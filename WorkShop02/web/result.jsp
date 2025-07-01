<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Exam Result: ${exam.examTitle}</title>
    
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inter:wght@400;700&display=swap" rel="stylesheet">
    <link href="assets/css/styles.css" rel="stylesheet">
    <link href="assets/css/result.css" rel="stylesheet">
</head>
<body>
    <div class="header-container">
        <h1>Exam Result</h1>
    </div>
    
    <div class="horizontal-line"></div>

    <div class="result-box">
        <h2>Exam Result: ${fn:escapeXml(exam.examTitle)}</h2>

        <p><strong>Subject:</strong> ${fn:escapeXml(exam.subject)}</p>
        <p><strong>Total Questions:</strong> <span class="highlight">${totalQuestions}</span></p>
        <p><strong>Correct Answers:</strong> <span class="highlight">${correctCount}</span></p>
        <p><strong>Your Score:</strong> <span class="highlight">${score}</span> / ${exam.totalMarks}</p>
        
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="toExamManagement"/>
            <button type="submit" class="btn btn-primary">Back to Exam Management</button>
        </form>
    </div>
</body>
</html>