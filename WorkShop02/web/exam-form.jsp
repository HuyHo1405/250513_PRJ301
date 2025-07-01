<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Exam Form</title>
    
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inter:wght@400;700&display=swap" rel="stylesheet">
    <link href="assets/css/styles.css" rel="stylesheet">
    <link href="assets/css/examForm.css" rel="stylesheet">
    
    <script src="assets/js/examFormScript.js"></script>
</head>
<body>
    <div class="header-container">
        <h1>Exam ${actionType == 'updateExam' ? 'Edit' : 'Add'} Form</h1>
    </div>
    
    <div class="horizontal-line"></div>

    <div class="form-container">
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="${actionType == 'updateExam' ? 'updateExam': 'createExam'}" />

            <c:if test="${not empty param.examId}">
                <input type="hidden" name="examId" value="${param.examId}" />
            </c:if>

            <div class="sticky-action-bar">
                <button type="button" onclick="window.location.href='MainController?action=toExamManagement'" class="btn btn-primary">Back</button>
                <button type="submit" class="btn btn-primary">${actionType == 'updateExam' ? 'Update' : 'Add'} Exam</button>
            </div>

            <div class="form-layout">
                <div class="form-group full-width">
                    <label>Title:</label>
                    <input type="text" name="examTitle" value="${fn:escapeXml(param.examTitle)}" required>
                </div>

                <div class="form-row four-columns">
                    <div class="form-group">
                        <label>Subject:</label>
                        <input type="text" name="examSubject" value="${fn:escapeXml(param.examSubject)}" required>
                    </div>

                    <div class="form-group">
                        <label>Exam Category:</label>
                        <select name="examCategoryId" required>
                            <option value="" disabled <c:if test="${empty param.examCategoryId}">selected</c:if>>--Category--</option>
                            <c:forEach var="cat" items="${categoryList}">
                                <option value="${cat.id}" <c:if test="${param.examCategoryId == cat.id.toString()}">selected</c:if>>
                                    ${fn:escapeXml(cat.name)}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Total Marks:</label>
                        <input type="number" name="examTotalMarks" value="${param.examTotalMarks}" required>
                    </div>

                    <div class="form-group">
                        <label>Duration (minutes):</label>
                        <input type="number" name="examDuration" value="${param.examDuration}" required>
                    </div>
                </div>
            </div>

            <h3>Questions:</h3>

            <div id="questions-section">
                <c:forEach var="q" items="${questionList}">
                    <input type="hidden" name="questionId" value="${q.id}" />
                    <table class="question-table">
                        <tr>
                            <td colspan="4">
                                <label>Câu hỏi:</label>
                                <input type="text" name="questionText" value="${fn:escapeXml(q.text)}" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>A:</label>
                                <input type="text" name="optionA" value="${fn:escapeXml(q.optionA)}" required>
                            </td>
                            <td rowspan="4" class="spacer-col"></td>
                            <td rowspan="4">
                                <label>Correct Answer:</label><br>
                                <select name="correctOption" required>
                                    <option value="">--Chọn--</option>
                                    <option value="A" <c:if test="${q.correctOption == 'A'}">selected</c:if>>A</option>
                                    <option value="B" <c:if test="${q.correctOption == 'B'}">selected</c:if>>B</option>
                                    <option value="C" <c:if test="${q.correctOption == 'C'}">selected</c:if>>C</option>
                                    <option value="D" <c:if test="${q.correctOption == 'D'}">selected</c:if>>D</option>
                                </select>
                            </td>
                            <td rowspan="4">
                                <button type="button" onclick="deleteQuestion(this)" class="btn btn-secondary">Xóa</button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>B:</label>
                                <input type="text" name="optionB" value="${fn:escapeXml(q.optionB)}" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>C:</label>
                                <input type="text" name="optionC" value="${fn:escapeXml(q.optionC)}" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>D:</label>
                                <input type="text" name="optionD" value="${fn:escapeXml(q.optionD)}" required>
                            </td>
                        </tr>
                    </table>
                </c:forEach>
            </div>

            <div id="question-container"></div>
            <button type="button" onclick="addQuestion()" class="btn btn-secondary btn-add-question">+ Add Question</button>
            
        </form>
    </div>

    <div class="page-bottom-actions">
        <button type="button" onclick="document.querySelector('form').submit();" class="btn btn-primary">
            ${actionType == 'updateExam' ? 'Update' : 'Add'} Exam
        </button>
    </div>

    <div id="toast" class="toast">
        <c:if test="${actionType == 'success1'}">Create Exam Successfully!</c:if>
        <c:if test="${actionType == 'success2'}">Update Exam Successfully!</c:if>
    </div>
</body>
</html>