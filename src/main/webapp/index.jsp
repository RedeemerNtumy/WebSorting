<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sorting Application</title>
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
        form {
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px 25px;
            border-radius: 8px;
            display: flex;
            flex-direction: column;
        }
        select, input[type="text"] {
            padding: 10px;
            margin-top: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            width: 100%;
        }
        button {
            background-color: #FF5A5F;
            color: white;
            border: none;
            padding: 10px;
            margin-top: 20px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #FF3B4E;
        }
        .footer {
            text-align: center;
            margin-top: 20px;
            font-size: 12px;
            color: #777;
        }
        .modal {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
            display: none;
            justify-content: center;
            align-items: center;
        }
        .modal-content {
            background: white;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            width: 50%;
        }
    </style>
</head>
<body>
<div>
    <h1>Welcome to the Sorting Arena</h1>
    <form action="/webSorting_war/api/sort" method="post" onsubmit="return validateForm()">
        <label for="type">Choose a Sorting Algorithm:</label>
        <select name="type" id="type">
            <option value="quick">Quick Sort</option>
            <option value="merge">Merge Sort</option>
            <option value="heap">Heap Sort</option>
            <option value="radix">Radix Sort</option>
            <option value="bucket">Bucket Sort</option>
        </select>
        <input type="text" name="numbers" id="numbers" placeholder="Enter numbers separated by commas" required>
        <button type="submit">Sort</button>
    </form>
    <div class="modal" id="errorModal">
        <div class="modal-content">
            <p id="errorText"></p>
            <button onclick="closeModal()">Close</button>
        </div>
    </div>
    <p class="footer">Powered by <strong>Redeemer's Algorithms</strong></p>
</div>
<script>
    function validateForm() {
        var input = document.getElementById('numbers').value;
        if (!input.split(',').every(n => !isNaN(parseFloat(n)))) {
            document.getElementById('errorText').textContent = 'Please enter valid numbers separated by commas.';
            document.getElementById('errorModal').style.display = 'flex';
            return false;
        }
        return true;
    }
    function closeModal() {
        document.getElementById('errorModal').style.display = 'none';
    }
</script>
</body>
</html>
