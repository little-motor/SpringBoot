// 存放前端重用的一些方法，方便统一管理和完善方法
// author: little motor
// lastModified: 19.3.22

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
 *               科一全过
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
function combineMessage(reminderState,message){
    var a = "<div style=\"margin-top: -2.5%\" class=\"alert alert-dismissible ";
    var c = "\" role=\"alert\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>";
    var e = "</div>";
    return a + reminderState + c + message + e;
}

function ajax(data,method) {
    var xhr = new XMLHttpRequest();
    //需要在open()方法之前调用onreadystatechange事件，确保跨浏览器的兼容性
    xhr.onreadystatechange = function () {
        //状态从0-4，4表示接受了全部响应可以在客户端使用
        if (xhr.readyState == 4) {
            if ((xhr.status >= 200 && xhr.status <= 300) || xhr.status == 304) {
                document.getElementById("reminder").innerHTML = combineMessage("alert-success", "注册成功");
            } else {
                var httpHeader = xhr.getResponseHeader("wrongMessage");
                switch(httpHeader)
                {
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
    xhr.open(method, "/register", true);
    //在open和send之间设置请求头
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    console.log("string is: " + JSON.stringify(data));
    //发送数据到服务器
    xhr.send(JSON.stringify(data));
}