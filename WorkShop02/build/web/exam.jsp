<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Take Exam: ${exam.examTitle}</title>

    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inter:wght@400;700&display=swap" rel="stylesheet">
    <link href="assets/css/styles.css" rel="stylesheet">
    <link href="assets/css/exam.css" rel="stylesheet">
</head>
<body>
    <div class="header-container">
        <h1>Exam: ${exam.examTitle}</h1>
        <span id="countdown-timer" style="margin-left: 30px; font-size: 1.5em; color: #f26225;"></span>
    </div>

    <div class="horizontal-line"></div>

    <div class="form-container">
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="calculateResult" />
            <input type="hidden" name="examId" value="${exam.id}" />

            <c:forEach var="q" items="${questionList}" varStatus="loop">
                <div class="question-block">
                    <p class="question-title">Question ${loop.index + 1}: ${fn:escapeXml(q.text)}</p>
                    <div class="option">
                        <input type="radio" id="q${q.id}A" name="q${q.id}" value="A" required />
                        <label for="q${q.id}A">A. ${fn:escapeXml(q.optionA)}</label>
                    </div>
                    <div class="option">
                        <input type="radio" id="q${q.id}B" name="q${q.id}" value="B" />
                        <label for="q${q.id}B">B. ${fn:escapeXml(q.optionB)}</label>
                    </div>
                    <div class="option">
                        <input type="radio" id="q${q.id}C" name="q${q.id}" value="C" />
                        <label for="q${q.id}C">C. ${fn:escapeXml(q.optionC)}</label>
                    </div>
                    <div class="option">
                        <input type="radio" id="q${q.id}D" name="q${q.id}" value="D" />
                        <label for="q${q.id}D">D. ${fn:escapeXml(q.optionD)}</label>
                    </div>
                </div>
            </c:forEach>
        </form>
    </div>

    <div class="page-bottom-actions">
        <button type="submit" form="mainForm" class="btn btn-primary">Submit Exam</button>
    </div>
</body>
</html>