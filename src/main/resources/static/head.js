/**
 * 通用的head头文件，方便管理和修改
 * @author littlemotor
 * @date 19.4.13
 */

document.writeln("<!-- The justified navigation menu is meant for single line per list item.");
document.writeln("         Multiple lines will require custom code not provided by Bootstrap. -->");
document.writeln("<nav class=\'navbar navbar-default navbar-fixed-top\'>");
document.writeln("    <div class=\'container-fluid\'>");
document.writeln("        <!-- Brand and toggle get grouped for better mobile display -->");
document.writeln("        <div class=\'navbar-header\'>");
document.writeln("            <a class=\'navbar-brand\' href=\'#\'>Brand</a>");
document.writeln("        </div>");
document.writeln("");
document.writeln("        <form class=\'navbar-form navbar-left\'>");
document.writeln("            <div class=\'form-group\'>");
document.writeln("                <input type=\'text\' class=\'form-control\' placeholder=\'Search\'>");
document.writeln("            </div>");
document.writeln("            <button type=\'submit\' class=\'btn btn-default\'>搜索</button>");
document.writeln("        </form>");
document.writeln("        <!-- Collect the nav links, forms, and other content for toggling -->");
document.writeln("        <div class=\'collapse navbar-collapse\' id=\'bs-example-navbar-collapse-1\'>");
document.writeln("            <ul class=\'nav navbar-nav\'>");
document.writeln("                <li><a href=\'#\'>社区</a></li>");
document.writeln("                <li><a href=\'/message\'>留言</a></li>");
document.writeln("                <li><a href=\'#\'>介绍</a></li>");
document.writeln("            </ul>");
document.writeln("");
document.writeln("            <!--根据是否登陆显示不同效果-->");
document.writeln("            <ul class=\'nav navbar-nav navbar-right\'>");
document.writeln("                <li class=\'logout\'><a href=\'#\'>注册</a></li>");
document.writeln("                <li class=\'logout\'><a href=\'/login\'>登陆</a></li>");
document.writeln("                <li class=\'dropdown login\'>");
document.writeln("                    <a class=\'dropdown-toggle\' id=\'dropdownMenu1\'");
document.writeln("                       data-toggle=\'dropdown\'>个人中心");
document.writeln("                        <span class=\'caret\'></span></a>");
document.writeln("                    <ul class=\'dropdown-menu\' role=\'menu\' aria-labelledby=\'dropdownMenu1\'>");
document.writeln("                        <!--<li role=\'presentation\' class=\'dropdown-header\'>下拉菜单标题</li>-->");
document.writeln("                        <li role=\'presentation\'>");
document.writeln("                            <a role=\'menuitem\' href=\'#\'>个人主页</a>");
document.writeln("                        </li>");
document.writeln("                        <li role=\'presentation\'>");
document.writeln("                            <a role=\'menuitem\' href=\'/message\'>留言</a>");
document.writeln("                        </li>");
document.writeln("                        <li role=\'presentation\'>");
document.writeln("                            <a role=\'menuitem\' href=\'#\'>账号</a>");
document.writeln("                        </li>");
document.writeln("");
document.writeln("                        <li role=\'presentation\' class=\'divider\'></li>");
document.writeln("                        <!--<li role=\'presentation\' class=\'dropdown-header\'>下拉菜单标题</li>-->");
document.writeln("                        <li role=\'presentation\'>");
document.writeln("                            <a role=\'menuitem\' onclick=\'logout()\'>注销</a>");
document.writeln("                        </li>");
document.writeln("                    </ul>");
document.writeln("                </li>");
document.writeln("            </ul>");
document.writeln("        </div><!-- /.navbar-collapse -->");
document.writeln("    </div><!-- /.container-fluid -->");
document.writeln("</nav>");