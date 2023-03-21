<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Create Post</title>
    <link rel="stylesheet"
          type="text/css" href="<@spring.url '/css/style.css'/>"/>
</head>
<body>
    <#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
    </#if>

    <div>
        <fieldset>
            <legend>Create Post</legend>
            <form name="post" action="create" method="POST">
                Title: <@spring.formInput "postDto.title" "" "text"/>  <br/>
                Description: <@spring.formInput "postDto.description" "" "text"/> <br/>
                Content: <@spring.formInput "postDto.content" "" "text"/> <br/>
            <input type="submit" value="Create"/>
            </form>
        </fieldset>
    </div>
</body>
        </html>