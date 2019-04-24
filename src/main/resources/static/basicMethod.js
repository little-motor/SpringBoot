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
 * 定义发送message的对象，在JSON序列化过程中null的消息会自动被去除
 * @type {{content: undefined}}
 */
var message = {
    userId: undefined,
    messageData: undefined,
    likeNum: undefined,
    comments: undefined
};

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
 * 根据cookie的key获取相应的value，注意区分只有一个cookie的情况
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
 * 负责根据用户的登陆状态进行页面布局的调整
 */
function loginState(){
    var login = getCookie("login") == "true";
    //登陆状态
    if(login){
        var elements = document.getElementsByClassName("logout");
        for (var i=0,length=elements.length; i<length; i++)
        {
            elements[i].className += " hidden";
        }
    }
    //非登陆状态
    else {
        var elements = document.getElementsByClassName("login");
        for (var i=0,length=elements.length; i<length; i++)
        {
            elements[i].className += " hidden";
        }
    }
}
