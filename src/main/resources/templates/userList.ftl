<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
</head>
<body>
<table>
    <tr>
        <th>id</th><th>用户名</th><th>密码</th><th>邮箱</th>
    </tr>
    <#list users as user>
    <tr>
        <td>${user.id}</td> <td>${user.userName}</td><td>${user.passWord}</td><td>${user.userSex}</td>
    </tr>
    </#list>
</table>
</body>
</html>