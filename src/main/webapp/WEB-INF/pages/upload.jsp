<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/15 0015
  Time: 下午 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<!-- 文件上传必须 method="post" enctype="multipart/form-data" -->
<h2>单文件上传</h2>
<form action="do_upload" method="post" enctype="multipart/form-data">
    <input type="file" name="myFile"/>
    <input type="submit" value="上传"/>
</form>
<hr>
<h2>多文件上传</h2>
<form action="do_eupload" method="post" enctype="multipart/form-data">
    <input type="file" name="files"/>
    <input type="file" name="files"/>
    <input type="file" name="files"/>
    <input type="submit" value="上传"/>
</form>

<hr>
<img id="img" src="" width="200" height="200" style="display: none"/>
<hr>
<h2>异步上传</h2>
<input type="file" name="ajaxfile" id="file"/>
<input type="button" value="上传" onclick="upload()"/>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
    function  upload() {
        //js file 对象
        var file=$("#file")[0].files[0];

        //js form
        var form=new FormData();
        form.append("ajaxfile",file);

        //jquery ajax
        var opt={
            url:"ajax_upload",
            type:"post",
            contentType:false,
            processData:false,
            data:form,
            success:function(data){
                var json=eval("("+data+")");
                $("#img").attr("src","/upload/"+json.url);
                $("#img").attr('style','display:block')
            }
        };
        $.ajax(opt);
    }

</script>
</body>
</html>
