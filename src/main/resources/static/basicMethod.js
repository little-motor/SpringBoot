// 存放前端重用的一些方法，方便统一管理和完善方法
// author: little motor
// lastModified: 19.4.9

/***
 *      ┌─┐       ┌─┐ + +
 *   ┌──┘ ┴───────┘ ┴──┐++
 *   │                 │
 *   │       ───       │++ + + +
 *   ███████───███████ │+
 *   │                 │+
 *   │       ─┴─       │
 *   │                 │
 *   └───┐         ┌───┘
 *       │         │
 *       │         │   + +
 *       │         │
 *       │         └──────────────┐
 *       │                        │
 *       │                        ├─┐
 *       │                        ┌─┘
 *       │                        │
 *       └─┐  ┐  ┌───────┬──┐  ┌──┘  + + + +
 *         │ ─┤ ─┤       │ ─┤ ─┤
 *         └──┴──┘       └──┴──┘  + + + +
 *                神兽保佑
 *               统统没有bug
 *              科一二三四全过
 */
/**
 * 定义登陆的用户对象，在JSON序列化过程中null的消息会自动被去除
 * @type {{name: undefined, email: undefined, password: undefined, rememberMe: undefined}}
 */
var user = {
    name: undefined,
    email: undefined,
    password: undefined,
    rememberMe: undefined
};

/**
 * 负责根据用户的登陆状态进行页面布局的调整
 */
function loginState() {
    var login = getCookie("login") == "true";
    //登陆状态
    if (login) {
        var elements = document.getElementsByClassName("logout");
        for (var i = 0, length = elements.length; i < length; i++) {
            elements[i].className += " hidden";
        }
    }
    //非登陆状态
    else {
        var elements = document.getElementsByClassName("login");
        for (var i = 0, length = elements.length; i < length; i++) {
            elements[i].className += " hidden";
        }
    }
}

/**
 * 负责组合提示的消息，其中reminderState传递提示的状态（可选的有alert-success、alert-info、alert-warning、alert-danger）
 * message传递提示的具体消息
 * @param state
 * @param content
 * @returns {string}
 */
function combineMessage(state, content) {
    var a = "<div style=\"margin-top: -2.5%\" class=\"alert alert-dismissible ";
    var c = "\" role=\"alert\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>";
    var e = "</div>";
    return a + state + c + content + e;
}

/**
 * 根据cookie的key获取相应的value
 * @param key
 * @returns {string}
 */
function getCookie(key) {
    if (document.cookie.indexOf(";") == -1) {
        var arr1 = document.cookie.split("=");
        return arr1[1];
    } else {
        //由于cookie是通过一个分号+空格的形式串联起来的，所以这里需要先按分号空格截断,变成[name=Jack,pwd=123456,age=22]数组类型；
        var arr1 = document.cookie.split("; ");
        for (var i = 0; i < arr1.length; i++) {
            //通过=截断，把name=Jack截断成[name,Jack]数组；
            var arr2 = arr1[i].split("=");
            if (arr2[0] == key) {
                return decodeURI(arr2[1]);
            }
        }
    }
}


// /**
//  * ajax的公用方法
//  * @param data
//  * @param method
//  */
// function ajax(data, method, uri, contentType,func,xhr) {
//     //需要在open()方法之前调用onreadystatechange事件，确保跨浏览器的兼容性
//     xhr.onreadystatechange = func;
//     //启动请求
//     xhr.open(method, uri, true);
//     //在open和send之间设置请求头
//     xhr.setRequestHeader("Content-Type", contentType);
//     //post方法里面需要加入csrfToken
//     xhr.setRequestHeader("X-CSRF-TOKEN", getCookie("X-CSRF-TOKEN"));
//     //发送数据到服务器
//     xhr.send(data);
// }

/**
 * 序列化表单的公共方法
 * @param id
 */
function serializeForm(id) {
    var form = document.getElementById(id);
    var parts = [],
        elements = form.elements,
        i = 0,
        length = elements.length,
        filed = null;
    for (; i < length; i++) {
        filed = elements[i];
        switch (filed.type) {
            case "select-one":
            case "select-multiple":
                if (filed.name.length) {
                    var j = 0,
                        opt,
                        optLen = filed.options.length;
                    for (; j < optLen; j++) {
                        opt = filed.options[j];
                        if (opt.selected) {
                            parts.push(encodeURIComponent(filed.name) + "=" + encodeURIComponent(opt.value));
                        }
                    }
                }
                break;
            case undefined:
            case "submit":
            case "reset":
            case "file":
            case "button":
                break;
            case "radio":
            case "checkbox":
                if (!filed.checked) {
                    break;
                }
            default:
                if (filed.name.length && filed.value) {
                    //注意要进行url编码
                    parts.push(encodeURIComponent(filed.name) + "=" + encodeURIComponent(filed.value));
                }
        }

    }
    return parts.join("&");
}

/**
 * 在页面显示由getMessage调用ajax之后异步返回的message list
 * @param responseText 返回的responseText
 * @param displayNum 页面展示的数量
 */
function showMessageList(responseText, displayNum) {
    if (responseText != null) {
        message = JSON.parse(responseText);
        //循环遍历显示列表，目前最多显示最近的5个元素
        for (var index = 0; ((index < message.length) && index < displayNum); index++) {
            //注意messageData有null的情况
            if (message[index].messageData != null) {
                //postedMessages包含media节点，每个media节点包括message、comment、like等信息
                var postedMessages = document.getElementById("postedMessages");

                //创建media div元素
                var createDivMedia = document.createElement("div");
                createDivMedia.className = "media";

                //创建media-left div元素
                var createDivMediaLeft = document.createElement("div");
                createDivMediaLeft.className = "media-left col-md-3";
                var createLeftTextNode = document.createElement("h1")
                //日期注意传过来的是时间戳转换为本地时间
                var elementDateTime = document.createTextNode(new Date(message[index].messageCreateDate).toLocaleDateString().replace(/2019\//, ""));
                createLeftTextNode.appendChild(elementDateTime);
                createDivMediaLeft.appendChild(createLeftTextNode);

                //创建media-body元素
                var createDivMediaBody = document.createElement("div");
                createDivMediaBody.className = "media-body";
                createDivMediaBody.id = message[index].messageId;
                var createBodyTextNode = document.createElement("h3");
                //消息具体内容
                var elementMessage = document.createTextNode(message[index].messageData);
                createBodyTextNode.appendChild(elementMessage);
                createDivMediaBody.appendChild(createBodyTextNode);

                // //删除按键，注意判断用户
                // if (commentData[commentIndex].userId == getCookie("userId")) {
                //     var createCommentDeleteNode = document.createElement("a");
                //     createCommentDeleteNode.setAttribute("onclick", "deleteComment(this)");
                //     createCommentDeleteNode.className = "pull-right";
                //     var elementCommentDeleteData = document.createTextNode("删除");
                //     createCommentDeleteNode.appendChild(elementCommentDeleteData);
                //     createPanelBodyNode.appendChild(createCommentDeleteNode);
                // }

                //爱心icon标签
                var createIconHeartNode = document.createElement("span");
                createIconHeartNode.className = "glyphicon glyphicon-heart-empty";
                //绑定点赞函数，并且只会执行一次
                createIconHeartNode.addEventListener("click", like.bind(this), {once: true});
                var createHeartNumNode = document.createElement("span");
                createHeartNumNode.className = "likeNum";
                var elementLikeNum = document.createTextNode(message[index].likeNum);
                createHeartNumNode.appendChild(elementLikeNum);

                //评论icon标签
                var createIconCommentNode = document.createElement("span");
                createIconCommentNode.className = "glyphicon glyphicon-comment";
                //绑定评论展开函数
                createIconCommentNode.addEventListener("click", unFold.bind(this), {once: true});
                var commentNum = 0;
                if ((message[index].comments.length == 1) && (message[index].comments[0].commentData == null)) {
                    commentNum = 0;
                } else {
                    commentNum = message[index].comments.length;
                }
                var createCommentNumNode = document.createElement("span");
                createCommentNumNode.className = "commentNum";
                var elementCommentNum = document.createTextNode(commentNum);
                createCommentNumNode.appendChild(elementCommentNum);

                createDivMediaBody.appendChild(createIconHeartNode);
                createDivMediaBody.appendChild(createHeartNumNode);
                createDivMediaBody.appendChild(createIconCommentNode);
                createDivMediaBody.appendChild(createCommentNumNode);

                //一个完整了message 节点组装
                //组装左部日期
                createDivMedia.appendChild(createDivMediaLeft);
                //组装右部message
                createDivMedia.appendChild(createDivMediaBody);
                //组装评论具体内容,默认隐藏
                createDivMedia = alreadyExistComment(message[index].comments, createDivMedia);

                //将组装好的单个media节点装入postedMessages
                postedMessages.appendChild(createDivMedia);
            }
        }
        console.log("数组大小：" + message.length);
    }
}

/**
 * 预处理评论列表，默认隐藏
 */
function alreadyExistComment(commentData, createDivMedia) {

    var createCommentList = document.createElement("div");
    createCommentList.className = "comment hidden";

    //已评论列表
    if (commentData[0].commentData != null) {
        for (var commentIndex = 0; commentIndex < commentData.length; commentIndex++) {
            //用于删除评论之后的提示消息
            var createCommentTipsNode = document.createElement("div");
            createCommentTipsNode.className = "commentTips";

            //单个完整的评论节点
            var createCommentNode = document.createElement("div");
            createCommentNode.className = "panel panel-default";
            //panel-heading，包括用户名和评论时间
            var createPanelHeadingNode = document.createElement("div");
            createPanelHeadingNode.className = "panel-heading";
            //用户名节点
            var createUserNameNode = document.createElement("div");
            createUserNameNode.className = "col-md-3";
            var elementUserName = document.createTextNode(commentData[commentIndex].userName);
            createUserNameNode.appendChild(elementUserName);
            //评论时间节点
            var createCommentDateNode = document.createElement("div");
            createCommentDateNode.className = "col-md-offset-10";
            var elementCommentDate = document.createTextNode(new Date(commentData[commentIndex].commentCreateDate).toLocaleDateString());
            createCommentDateNode.appendChild(elementCommentDate);
            createPanelHeadingNode.appendChild(createUserNameNode);
            createPanelHeadingNode.appendChild(createCommentDateNode);

            //评论的具体内容
            var createPanelBodyNode = document.createElement("div");
            createPanelBodyNode.className = "panel-body";
            createPanelBodyNode.id = commentData[commentIndex].commentId;
            var elementCommentData = document.createTextNode(commentData[commentIndex].commentData);
            createPanelBodyNode.appendChild(elementCommentData);
            //删除按键，注意判断用户
            if (commentData[commentIndex].userId == getCookie("userId")) {
                var createCommentDeleteNode = document.createElement("a");
                createCommentDeleteNode.setAttribute("onclick", "deleteComment(this)");
                createCommentDeleteNode.className = "pull-right";
                var elementCommentDeleteData = document.createTextNode("删除");
                createCommentDeleteNode.appendChild(elementCommentDeleteData);
                createPanelBodyNode.appendChild(createCommentDeleteNode);
            }

            //组装panel-heading和panel-body
            createCommentNode.appendChild(createPanelHeadingNode);
            createCommentNode.appendChild(createPanelBodyNode);

            //组装已评论信息
            createCommentList.appendChild(createCommentTipsNode);
            createCommentList.appendChild(createCommentNode);
        }
    }

    //评论输入框
    var createInputNode = document.createElement("input");
    createInputNode.setAttribute("type", "text");
    createInputNode.className = "form-control";

    //用于发送评论之后的提示消息
    var createCommentTipsNode = document.createElement("div");
    createCommentTipsNode.className = "commentTips";

    //form-group div
    var createFormGroupNode = document.createElement("div");
    createFormGroupNode.className = "form-group";
    createFormGroupNode.appendChild(createInputNode);

    //评论按钮
    var createButtonNode = document.createElement("button");
    createButtonNode.setAttribute("type", "button");
    createButtonNode.className = "btn btn-success pull-right";
    var createButtonNameNode = document.createTextNode("评论");
    createButtonNode.appendChild(createButtonNameNode);
    createButtonNode.addEventListener("click", submitComment.bind(this))

    //评论form表组装
    var createCommentAreaNode = document.createElement("form");
    createCommentAreaNode.appendChild(createCommentTipsNode);
    createCommentAreaNode.appendChild(createFormGroupNode);
    createCommentAreaNode.appendChild(createButtonNode);

    createCommentList.appendChild(createCommentAreaNode);
    createDivMedia.appendChild(createCommentList);

    //已评论列表
    // <div class="hidden panel panel-default">
    //     <div class="panel-heading">
    //         <div class="col-sm-1">name</div>
    //         <div class="col-sm-offset-6">timestampt</div>
    //     </div>
    //     <div class="panel-body">
    //         comment
    //     </div>
    // </div>

    //评论输入框
    // <form>
    //
    //     <div class="form-group">
    //
    //         <input type="text" class="form-control">
    //         </div>
    //         <button type="button" class="btn btn-success pull-right">默认按钮</button>
    //
    //         </form>
    return createDivMedia;
}

/**
 * 新建一个评论节点，并且删除评论输入框的信息
 * @param node
 */
function appendPostedComment(xhr, node) {
    //用于删除评论之后的提示消息
    var createCommentTipsNode = document.createElement("div");
    createCommentTipsNode.className = "commentTips";

    //单个完整的评论节点
    var createCommentNode = document.createElement("div");
    createCommentNode.className = "panel panel-default";
    //panel-heading，包括用户名和评论时间
    var createPanelHeadingNode = document.createElement("div");
    createPanelHeadingNode.className = "panel-heading";
    //用户名节点
    var createUserNameNode = document.createElement("div");
    createUserNameNode.className = "col-md-3";
    var elementUserName = document.createTextNode(getCookie("userName"));
    createUserNameNode.appendChild(elementUserName);
    //评论时间节点
    var createCommentDateNode = document.createElement("div");
    createCommentDateNode.className = "col-md-offset-10";
    var elementCommentDate = document.createTextNode(new Date().toLocaleDateString());
    createCommentDateNode.appendChild(elementCommentDate);
    createPanelHeadingNode.appendChild(createUserNameNode);
    createPanelHeadingNode.appendChild(createCommentDateNode);

    //评论的具体内容
    var createPanelBodyNode = document.createElement("div");
    createPanelBodyNode.className = "panel-body";
    //评论id由服务器返回
    createPanelBodyNode.id = xhr.responseText;
    var elementCommentData = document.createTextNode(node.target.previousElementSibling.firstChild.value);
    createPanelBodyNode.appendChild(elementCommentData);
    //删除按键
    var createCommentDeleteNode = document.createElement("a");
    createCommentDeleteNode.setAttribute("onclick", "deleteComment(this)");
    createCommentDeleteNode.className = "pull-right";
    var elementCommentDeleteData = document.createTextNode("删除");
    createCommentDeleteNode.appendChild(elementCommentDeleteData);
    createPanelBodyNode.appendChild(createCommentDeleteNode);

    //组装panel-heading和panel-body
    createCommentNode.appendChild(createPanelHeadingNode);
    createCommentNode.appendChild(createPanelBodyNode);
    //组装已评论信息
    var createCommentList = node.target.parentElement.parentNode;
    //注意此处是在指定的评论输入框之前添加节点
    createCommentList.insertBefore(createCommentTipsNode, node.target.parentNode);
    createCommentList.insertBefore(createCommentNode, node.target.parentNode);

    //删除评论框信息
    node.target.previousElementSibling.firstChild.value = null;
}

function logined(tipId){
    if(getCookie("loign") == "false"){
        document.getElementById(tipId).innerHTML = combineMessage("alert-success", "请先登陆");
        return false;
    } else{
        return true;
    }

}
/**
 * 注销用户
 */
function logout() {
    var uri = "/logout";
    ajax("", "POST", uri, "application/x-www-form-urlencoded");
}