package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.dao.ExamDAO;
import model.dao.QuestionDAO;
import model.dto.ExamDTO;
import model.dto.QuestionDTO;
import utils.CUtils;
import utils.ValidationUtils;

@WebServlet(name = "SubmitExamServlet", urlPatterns = {"/SubmitExamServlet"})
public class SubmitExamServlet extends HttpServlet {

    //static fields
    private static final String WELCOME = "welcome.jsp";
    private static final String RESULT = "result.jsp";
    
    private static final ExamDAO EDAO = new ExamDAO();
    private static final QuestionDAO QDAO = new QuestionDAO();
    
    //process
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = "";
        try {
            String action = request.getParameter("action");

            if(action.equals("calculateResult")){
                url = handleCalculateResult(request, response);
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

    //handle methods 
    private String handleCalculateResult(HttpServletRequest request, HttpServletResponse response) {
        int examId = CUtils.toInt(request.getParameter("examId"));

        if (ValidationUtils.isInvalidId(examId)) {
            return CUtils.error(request, "Invalid Exam ID.");
        }

        List<QuestionDTO> questionList = QDAO.retrieve("exam_id = ?", examId);
        int totalQuestions = questionList.size();
        
        int correctCount = 0;
        for (QuestionDTO q : questionList) {
            String userAnswer = request.getParameter("q" + q.getId());
            if (userAnswer != null && userAnswer.equalsIgnoreCase(q.getCorrectOption())) {
                correctCount++;
            }
        }

        ExamDTO exam = EDAO.getFirst("exam_id = ?", examId);
        int totalMarks = exam.getTotalMarks();
        int score = (int) Math.round(((double) correctCount / totalQuestions) * totalMarks);

        request.setAttribute("exam", exam);
        request.setAttribute("correctCount", correctCount);
        request.setAttribute("totalQuestions", totalQuestions);
        request.setAttribute("score", score);

        return RESULT;
    }

}
