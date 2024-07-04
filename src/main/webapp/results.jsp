<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sorting Results</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            color: #484848;
            background-color: #f7f7f7;
            margin: 0;
            padding: 40px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h1 {
            color: #FF5A5F;
            text-align: center;
            font-weight: 300;
        }

        .content {
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px 25px;
            border-radius: 8px;
            text-align: center;
        }

        .result {
            color: #333;
            margin: 20px 0;
            font-size: 16px;
            line-height: 1.5;
        }

        a {
            display: inline-block;
            background-color: #FF5A5F;
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: #FF3B4E;
        }
    </style>
</head>
<body>
<div class="content">
    <h1>Sorting Results</h1>
    <p class="result">Sorted using <strong><%= request.getAttribute("sortType") %> sort</strong>: <span><%= request.getAttribute("sortedNumbers") %></span></p>
    <a href="/webSorting_war/api/home">Home</a>
</div>
</body>
</html>
