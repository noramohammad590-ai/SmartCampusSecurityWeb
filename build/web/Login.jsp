<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html><html><head><meta charset="UTF-8">

<title>Smart Campus Security - Login</title>

<style>

    body {
        font-family: Arial, sans-serif;
        background: #eef3f8;

        display: flex;
        justify-content: center;
        align-items: center;

        min-height: 100vh;
        margin: 0;
    }

    .login-box {
        background: white;
        padding: 35px;
        width: 320px;

        border-radius: 12px;

        box-shadow: 0 4px 15px #aaa;
    }

    h2 {
        text-align: center;
        color: #173b67;
    }

    .info {
        text-align: center;
        color: #555;
        margin-bottom: 20px;
    }

    input {
        width: 100%;
        padding: 11px;
        margin: 10px 0;
        box-sizing: border-box;
    }

    button {
        width: 100%;
        padding: 12px;

        background: #1769aa;
        color: white;

        border: none;
        cursor: pointer;

        font-size: 16px;
    }

    button:hover {
        background: #12558c;
    }

</style>

</head><body><div class="login-box">

    <h2>Smart Campus Security</h2>

    <p class="info">
        Login using your ID and password
    </p>

    <form action="login" method="post">

        <input
            type="text"
            name="username"
            placeholder="Enter ID"
            required
            pattern="[0-9]+"
            title="Please enter numbers only">

        <input
            type="password"
            name="password"
            placeholder="Enter password"
            required>

        <button type="submit">
            Login
        </button>

    </form>

</div>

</body></html>