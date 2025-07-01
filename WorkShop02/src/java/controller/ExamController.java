package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.dao.ExamCategoryDAO;
import model.dao.ExamDAO;
import model.dao.QuestionDAO;
import model.dto.ExamDTO;
import model.dto.QuestionDTO;
import utils.CUtils;
import utils.ExamUtils;
import utils.ValidationUtils;

@WebServlet(name = "ExamController", urlPatterns = {"/ExamController"})
public class ExamController extends HttpServlet {

    //static fields
    private static final String EXAM_MANAGEMENT = "exam-management.jsp";
    private static final String EXAM_CATEGORY = "exam-category.jsp";
    private static final String EXAM_FORM = "exam-form.jsp";
    private static final String EXAM = "exam.jsp";
    private static final String WELCOME = "welcome.jsp";
    
    private static final ExamDAO EDAO = new ExamDAO();
    private static final ExamCategoryDAO ECDAO = new ExamCategoryDAO();
    private static final QuestionDAO QDAO = new QuestionDAO();
    
    //process
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = "";
        try {
            String action = request.getParameter("action");

            if (action.equals("toExamManagement")) {
                CUtils.setExamManagementAttributes(request, EDAO.retrieve("1=1"), ECDAO.retrieve("1=1"));
                url = EXAM_MANAGEMENT;
            }else if(action.equals("toExamCategory")){
                request.setAttribute("categoryList", ECDAO.retrieve("1=1"));
                url = EXAM_CATEGORY;
            }else if(action.equals("toAddExam")){
                request.setAttribute("categoryList", ECDAO.retrieve("1=1"));
                url = EXAM_FORM;
            }else if(action.equals("toExam")){
                url = handleToExam(request, response);
            }else if(action.equals("toEditExam")){
                url = handleToEditExam(request, response);
            }else if(action.equals("searchExam")){
                url = handleSearchExam(request, response);
            }else if(action.equals("createExam")){
                url = handleCreateExam(request, response);
            }else if(action.equals("updateExam")){
                url = handleUpdateExam(request, response);
            } else {
                url = WELCOME;
            }
        } catch (Exception e) {
            e.printStackTrace();
            url = CUtils.error(request, "Internal Error!");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    //doGet & doPost
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    // helping methods
    private void saveOrUpdateQuestions(int examId, List<QuestionDTO> questionList) {
        for (QuestionDTO q : questionList) {
            q.setExamId(examId);
            if (q.getId() == -1) {
                QDAO.create(q);
            } else {
                QDAO.update(q);
            }
        }
    }
    
    //handle methods
    private String handleToEditExam(HttpServletRequest request, HttpServletResponse response) {
        int examId = CUtils.toInt(request.getParameter("examId"));
        
        if(ValidationUtils.isInvalidId(examId)){
            return CUtils.error(request, "Id Not Found!");
        }
        
        CUtils.setExamFormAttributes(request, QDAO.retrieve("exam_id = ?", examId), ECDAO.retrieve("1=1"), 
                "updateExam", null);
        return EXAM_FORM;
    }
    
    private String handleToExam(HttpServletRequest request, HttpServletResponse response) {
        int examId = CUtils.toInt(request.getParameter("examId"));
        
        if(ValidationUtils.isInvalidId(examId)){
            return CUtils.error(request, "Id Not Found!");
        }
        
        request.setAttribute("exam", EDAO.getFirst("exam_id = ?", examId));
        request.setAttribute("questionList", QDAO.retrieve("exam_id = ?", examId));
        return EXAM;
    }
    
    private String handleSearchExam(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("strKeyword");
        int categoryId = CUtils.toInt(request.getParameter("examCategoryId"));
        
        List<ExamDTO> examList;
        if(ValidationUtils.isInvalidId(categoryId)){
            examList = EDAO.retrieve("exam_title LIKE ?", "%" + keyword + "%");
        }else{
            examList = EDAO.retrieve("exam_title LIKE ? AND category_id = ?", "%" + keyword + "%", categoryId);
        }
        
        CUtils.setExamManagementAttributes(request, examList, ECDAO.retrieve("1=1"));
        return EXAM_MANAGEMENT;
    }

    private String handleCreateExam(HttpServletRequest request, HttpServletResponse response) {
        ExamDTO exam = ExamUtils.getParamExam(request);
        List<QuestionDTO> questionList = ExamUtils.getQuestionList(request);
        
        if(ValidationUtils.isInvalidExam(exam) 
                || (questionList != null && ValidationUtils.isInvalidQuestions(questionList))){
            CUtils.setExamFormAttributes(request, questionList, ECDAO.retrieve("1=1"), 
                    "addExam", "Empty input parameters. Please check!");
            return EXAM_FORM;
        }
        
        Integer examId = EDAO.createReturnId(exam);
        if(examId == null){
            return CUtils.error(request, "Exam Id Not Found!");
        }
        
        if(questionList != null){
            saveOrUpdateQuestions(examId, questionList);
        }
        
        CUtils.setExamFormAttributes(request, questionList, ECDAO.retrieve("1=1"), 
                    "success1", null);
        return EXAM_FORM;
    }

    private String handleUpdateExam(HttpServletRequest request, HttpServletResponse response) {
        int examId = CUtils.toInt(request.getParameter("examId"));
        ExamDTO exam = ExamUtils.getParamExam(request);
        List<QuestionDTO> questionList = ExamUtils.getQuestionList(request);
        
        if(ValidationUtils.isInvalidExam(exam) || ValidationUtils.isInvalidId(examId)){
            return CUtils.error(request, "Exam Id Not Found!");
        }
        
        if(questionList != null && ValidationUtils.isInvalidQuestions(questionList)){
            CUtils.setExamFormAttributes(request, questionList, ECDAO.retrieve("1=1"), 
                "addExam", "Empty input parameters. Please check!");
            return EXAM_FORM;
        }
        
        EDAO.update(exam);
        saveOrUpdateQuestions(examId, questionList);
        
        CUtils.setExamFormAttributes(request, questionList, ECDAO.retrieve("1=1"), 
                "success2", null);
        return EXAM_FORM;
    }
}
