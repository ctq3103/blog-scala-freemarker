<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Update Post</title>
<body>
<#if errorMessage??>
<div style="color:red;font-style:italic;">
    ${errorMessage}
</div>
</#if>

<div>
    <fieldset>
        <legend>Update Post</legend>
        <form name="post" action="${postDto.id()}" method="POST">
            <@spring.formInput "postDto.id" "" "hidden"/>
            Title: <@spring.formInput "postDto.title" "" "text"/>  <br/>
            Description: <@spring.formInput "postDto.description" "" "text"/> <br/>
            Content: <@spring.formInput "postDto.content" "" "text"/> <br/>
            <input type="submit" value="Update"/>
        </form>
    </fieldset>
</div>
</body>
        </html>