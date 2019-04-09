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
 *               科一二三四全过
 *               统统没有bug
 */
//定义登陆的用户对象，在JSON序列化过程中null的消息会自动被去除
var user = {
    name: undefined,
    email: undefined,
    password: undefined,
    rememberMe: undefined
};

//负责组合提示的消息，其中reminderState传递提示的状态（可选的有alert-success、alert-info、alert-warning、alert-danger）
//message传递提示的具体消息
function combineMessage(reminderState, message) {
    var a = "<div style=\"margin-top: -2.5%\" class=\"alert alert-dismissible ";
    var c = "\" role=\"alert\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>";
    var e = "</div>";
    return a + reminderState + c + message + e;
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

/**
 * ajax的公用方法
 * @param data
 * @param method
 */
function ajax(data, method, uri, contentType) {
    var xhr = new XMLHttpRequest();
    //需要在open()方法之前调用onreadystatechange事件，确保跨浏览器的兼容性
    xhr.onreadystatechange = function () {
        //状态从0-4，4表示接受了全部响应可以在客户端使用
        if (xhr.readyState == 4) {
            if ((xhr.status >= 200 && xhr.status <= 300) || xhr.status == 304) {
                document.getElementById("reminder").innerHTML = combineMessage("alert-success", "注册成功");
            } else {
                var httpHeader = xhr.getResponseHeader("wrongMessage");
                switch (httpHeader) {
                    case "DuplicateKeyException":
                        document.getElementById("reminder").innerHTML = combineMessage("alert-danger", "邮箱已存在");
                        break;
                    default:
                        document.getElementById("reminder").innerHTML = combineMessage("alert-danger", "注册失败");
                }
            }
        }
    };
    //启动请求
    xhr.open(method, uri, true);
    //在open和send之间设置请求头
    xhr.setRequestHeader("Content-Type", contentType);
    //post方法里面需要加入csrfToken
    xhr.setRequestHeader("X-CSRF-TOKEN", getCookie("X-CSRF-TOKEN"));
    //发送数据到服务器
    xhr.send(data);
}

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
