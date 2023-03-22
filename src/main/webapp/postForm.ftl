<html>
<head>
    <title>Post Form</title>
</head>
<body>
<#if errorMessage??>
<div style="color:red;font-style:italic;">
    ${errorMessage}
</div>
</#if>

<div>
    <fieldset>
        <legend>
            <#if !isUpdate>Create Post</#if>
    </legend>
    <legend>
        <#if isUpdate>Update Post</#if>
</legend>

<form name="post" action="<#if !isUpdate>/posts/create</#if><#if isUpdate>/posts/update/${postDto.id()}</#if>" method="POST">

    <input type="hidden" name="id" value="<#if postDto.id()??>${postDto.id()}</#if>"/>
    Title: <input type="text" name="title" value="<#if postDto.title()??>${postDto.title()}</#if>"/> <br/>
    Description: <input type="text" name="description" value="<#if postDto.description()??>${postDto.description()}</#if>"/>
    <br/>
    Content: <input type="text" name="content" value="<#if postDto.content()??>${postDto.content()}</#if>"/><br/>
    <input type="submit" value="<#if !isUpdate>Create</#if><#if isUpdate>Update</#if>"/>
</form>
</fieldset>

</div>
</body>
</html>