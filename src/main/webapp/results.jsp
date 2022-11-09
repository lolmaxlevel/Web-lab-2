<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.lolmaxlevel.weblab2.misc.Result" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="icon" href="./src/img/icon.jpg">
    <link rel="stylesheet" type="text/css" href="./src/css/style.css">
    <title>Results</title>
</head>
<body>
    <div class="plate-top" >
        <h2 class="plate-top-title">Результат</h2>
    </div>

    <div class="scroll-container" id="table-plate">
        <table id="result-table">
            <thead class="table-header">
                <th class="coords-col">X</th>
                <th class="coords-col">Y</th>
                <th class="coords-col">R</th>
                <th class="time-col">Время</th>
                <th class="time-col">Время выполнения(ns)</th>
                <th class="hitres-col">Результат</th>
                <% ArrayList<Result> results = (ArrayList<Result>) pageContext.getServletContext().getAttribute("results");%>
            </thead>
            <% if (results != null) {for (Result result : results) {
            %>
            <%
                StringBuilder newRow = new StringBuilder("<tr>");
                newRow.append("<td class = 'x'>").
                        append(String.format("%.3f", result.getX()).replace(",", ".")).append("</td>");
                newRow.append("<td class = 'y'>").
                        append(String.format("%.3f", result.getY()).replace(",", ".")).append("</td>");
                newRow.append("<td class = 'r'>").
                        append(String.format("%.1f", result.getR()).replace(",", ".")).append("</td>");
                newRow.append("<td>").append(result.getTime().getHour()).append(":").append(result.getTime().getMinute()).
                        append(":").append(result.getTime().getSecond()).append("</td>");
                newRow.append("<td>").append(result.getExec_time()).append("</td>");
                String color = result.isHit() ? "green" : "red";
                newRow.append("<td class = 'hit'>" + "<span class=").
                        append(color).append(">").append(result.isHit()).append("</span>").append("</td>"); %>
            <%=newRow.toString()%>
            <% }} %>
        </table>
    </div>
    <div class="buttons-result">
        <button class="glow-on-hover" onclick="window.history.go(-1); return false;" type="submit" value="Cancel">Back</button>
    </div>
</body>
</html>
