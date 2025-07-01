<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Exam Management</title>
    
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inter:wght@400;700&display=swap" rel="stylesheet">
    <link href="assets/css/styles.css" rel="stylesheet">
    <link href="assets/css/examManagement.css" rel="stylesheet">
</head>
<body>
    <div class="header-container">
        <h1>${sessionScope.user.role} Exam Management</h1>
    </div>
    
    <div class="horizontal-line"></div>

    <div class="search-add-container">
        <div class="search-group">
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="searchExam">
                <input type="text" name="strKeyword"
                       value="${fn:escapeXml(param.strKeyword)}"
                       placeholder="Enter keyword...">
                <select name="examCategoryId">
                    <option value="0">--Category--</option>
                    <c:forEach var="cat" items="${categoryList}">
                        <option value="${cat.id}"
                            <c:if test="${param.examCategoryId == cat.id}">selected</c:if>>
                            ${cat.name}
                        </option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn btn-primary btn-search">Search</button>
            </form>
        </div>

        <c:if test="${sessionScope.user.role == 'Instructor'}">
            <div class="add-exam-group">
                <form action="MainController" method="POST">
                    <input type="hidden" name="action" value="toAddExam">
                    <button type="submit" class="btn btn-add-exam">+ Add Exam</button>
                </form>
            </div>
        </c:if>
    </div>
            
    <div class="table-wrapper">
        <table>
            <thead>
                <tr>
                    <th>Ord</th>
                    <th>Title</th>
                    <th>Subject</th>
                    <th>Category</th>
                    <th>Total Marks</th>
                    <th>Duration (min)</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="exam" items="${examList}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${exam.examTitle}</td>
                        <td>${exam.subject}</td>
                        <td>${mapCategoryName[exam.categoryId]}</td>
                        <td>${exam.totalMarks}</td>
                        <td>${exam.duration}</td>
                        <td>
                            <c:if test="${sessionScope.user.role == 'Instructor'}">
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="action" value="toEditExam">
                                    <input type="hidden" name="examId" value="${exam.id}">
                                    <input type="hidden" name="examTitle" value="${exam.examTitle}">
                                    <input type="hidden" name="examSubject" value="${exam.subject}">
                                    <input type="hidden" name="examCategoryId" value="${mapCategoryName[exam.categoryId]}">
                                    <input type="hidden" name="examTotalMarks" value="${exam.totalMarks}">
                                    <input type="hidden" name="examDuration" value="${exam.duration}">
                                    <button type="submit" class="btn btn-small btn-edit-full-width">Edit</button>
                                </form>
                            </c:if>

                            <form action="MainController" method="POST">
                                <input type="hidden" name="action" value="toExam">
                                <input type="hidden" name="examId" value="${exam.id}">
                                <button type="submit" class="btn btn-primary btn-small">Take the Test</button> 
                            </form>    
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty examList}">
                    <tr>
                        <td colspan="7" style="text-align:center;">No exams found.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
            
    <form action="MainController" method="post" class="back-button-form">
        <input type="hidden" name="action" value="toWelcome"/>
        <button type="submit" class="btn btn-primary">Back</button>
    </form>
</body>
</html>