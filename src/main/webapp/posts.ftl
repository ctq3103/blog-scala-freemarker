<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>All posts</title>
</head>
<body>
<h3>Posts</h3>
<a href="posts/create">Create post</a>
<br><br>
    <div>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
            </tr>
            <#list posts as post>
            <tr>
                <td>${post.id()}</td>
                <td>${post.title()}</td>
                <td>${post.description()}</td>
                <td><a href="posts/update/${post.id()}">Edit</a></td>
            </tr>
            </#list>
    </table>
</div>
</body>
</html>