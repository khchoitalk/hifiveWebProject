/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.32
 * Generated at: 2018-09-02 10:27:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.favorite;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import favorite.model.vo.Favorite;
import java.util.ArrayList;
import user.model.vo.User;

public final class favorite_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("/views/favorite/../../footer.jsp", Long.valueOf(1535623718000L));
    _jspx_dependants.put("/views/favorite/../../header.jsp", Long.valueOf(1535623792000L));
    _jspx_dependants.put("/views/favorite/../.././views/support/accessTerms.jsp", Long.valueOf(1535623716000L));
    _jspx_dependants.put("/views/favorite/../.././views/support/policy.jsp", Long.valueOf(1535623716000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("favorite.model.vo.Favorite");
    _jspx_imports_classes.add("user.model.vo.User");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"/hifive/resources/image/index/logo2.png\" />\r\n");
      out.write("<title>Traveler's Couch</title>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/hifive/resources/css/bootstrap.min.css\">\r\n");
      out.write("\r\n");
      out.write("<script src=\"/hifive/resources/js/jquery-3.3.1.min.js\"></script>\r\n");
      out.write("<script\r\n");
      out.write("   src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\"></script>\r\n");
      out.write("<script\r\n");
      out.write("   src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("/* 전체 사이즈 1000에 맞게 사이즈 해놨으니 안 바꾸셔도 될거에여.. */\r\n");
      out.write("\r\n");
      out.write("/* 여기 이새끼가 전체 우리 컨테이너 역할 */\r\n");
      out.write("\r\n");
      out.write("   .container{\r\n");
      out.write("   margin: 0 auto;\r\n");
      out.write("   padding: 10px;\r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("/* 이게  우리가 이제 만들어야할 공간 */\r\n");
      out.write("#main { \r\n");
      out.write("   width: 1000px;\r\n");
      out.write("   overflow: hidden;\r\n");
      out.write("}\r\n");
      out.write("/* 이게 왼쪽 메뉴부분!! float:left 가  다음 창을 붙여준다 */\r\n");
      out.write("#menu {\r\n");
      out.write("   width: 250px;\r\n");
      out.write("   margin: 5px 0 0 0;\r\n");
      out.write("   float: left;\r\n");
      out.write("}\r\n");
      out.write("/* 이게 오른쪽 본문 들어가는 부뷴 */\r\n");
      out.write("#content1 {\r\n");
      out.write("   width: 740px;\r\n");
      out.write("   margin: 5px 0 0 0;\r\n");
      out.write("   float: left;\r\n");
      out.write("   padding: 0 0 0 10px;\r\n");
      out.write("   text-align: center;\r\n");
      out.write("}\r\n");
      out.write("#content2 {\r\n");
      out.write("   width: 740px;\r\n");
      out.write("   margin: 5px 0 0 0;\r\n");
      out.write("   float: left;\r\n");
      out.write("   padding: 0 0 0 10px;\r\n");
      out.write("   text-align: center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".card-body{\r\n");
      out.write("   text-align: center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".searchdiv {\r\n");
      out.write("   text-align: center;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t $(function(){\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl : \"/hifive/favoritelist\",\r\n");
      out.write("            type : \"get\",\r\n");
      out.write("            data : {},            \r\n");
      out.write("            dataType : \"json\",\r\n");
      out.write("            success : function(data){\t\r\n");
      out.write("            \t//배열로 된 전송값을 직렬화해서 하나의 문자열로 바꿈\r\n");
      out.write("\t\t        var jsonStr = JSON.stringify(data);   \r\n");
      out.write("\t\t        //문자열을 json 객체로 바꿈\r\n");
      out.write("\t\t        var json = JSON.parse(jsonStr);\r\n");
      out.write("\t        \r\n");
      out.write("            \tvar value = \"\";                        \r\n");
      out.write("\r\n");
      out.write("            \tif(json.list.length == 0){\r\n");
      out.write("\t            \tvalue += \"선호하는 유저가 없습니다.\";\r\n");
      out.write("\t               $(\"#content1\").html($(\"#content1\").html()+value);   \r\n");
      out.write("\t            } else{\r\n");
      out.write("\t            \tvalue += \"<table cellpadding='15'><tr>\"\r\n");
      out.write("\t            \tfor(var i in json.list){\r\n");
      out.write("\t            \t\tvar address=\"입력안함\";\r\n");
      out.write("\t            \t\tvar nationality = \"입력안함\";\r\n");
      out.write("\t            \t  \tif(json.list[i].address != null)\r\n");
      out.write("\t            \t  \t\taddress = json.list[i].address;\r\n");
      out.write("\t            \t  \tif(json.list[i].nationality != null)\r\n");
      out.write("\t            \t  \t\tnationality = json.list[i].nationality;   \r\n");
      out.write("\t            \t  \t\r\n");
      out.write("\t            \t  \tif(json.list[i].image != null) { // 프로필 사진 있으면 (나중에 수정)\r\n");
      out.write("\t            \t  \t\tvalue += \"<td><div class='card' style='width: 200px;'>\" \r\n");
      out.write("\t            \t  \t\t+ \"<img class='card-img-top' height='200px' src='/hifive/resources/profileUpfiles/\"+ json.list[i].image + \"' alt='Card image cap'>\";\r\n");
      out.write("\t            \t  \t}\r\n");
      out.write("\t            \t    value += \"<div class='card-body'>\" \r\n");
      out.write("\t       \t\t       \t+ \"<a href='/hifive/profileinfo?userid=\" + json.list[i].f_userid \r\n");
      out.write("\t       \t\t        + \"'><h4 class='card-title'><b>\" + json.list[i].user_name + \"</b></h4></a>\"\r\n");
      out.write("\t       \t\t        + \"<p class='card-text'> <h6>\" + address + \"</h6> <b>\" + nationality + \"</b><br>\" \r\n");
      out.write("\t       \t\t        + \"<a href='/hifive/favoritedelete?f_userid=\" + json.list[i].f_userid + \"'>취소하기</a>\" + \" </p> </div> </div></td>\"\r\n");
      out.write("\t       \t\t        \r\n");
      out.write("\t       \t\t        if((i+1)%3==0)\r\n");
      out.write("\t       \t\t        \tvalue += \"</tr><tr>\";\r\n");
      out.write("\t       \t\t        if((i+1)==json.list.length)\r\n");
      out.write("\t       \t\t        \tvalue += \"</tr></table>\";\r\n");
      out.write("\t               \t}\r\n");
      out.write("\t               \t$(\"#content1\").html($(\"#content1\").html()+value); \r\n");
      out.write("\t              } \r\n");
      out.write("            }, // success\r\n");
      out.write("\t\t\terror : function(jqXHR, textstatus, errorThrown){\r\n");
      out.write("\t\t\t\tconsole.log(\"error : \" + jqXHR + \", \" + textstatus + \", \" + errorThrown);\r\n");
      out.write("\t\t\t} // error\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}); \r\n");
      out.write("\t\t \r\n");
      out.write("\t $(function(){      \r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t    $.ajax({\r\n");
      out.write("\t\t       url : \"/hifive/info\",\r\n");
      out.write("\t\t       type : \"post\",\r\n");
      out.write("\t\t       data : {userid : $(\"#userid\").val()},            \r\n");
      out.write("\t\t       dataType : \"json\",\r\n");
      out.write("\t\t       success : function(data){     \r\n");
      out.write("\t\t         \r\n");
      out.write("\t\t           // 프사\r\n");
      out.write("\t\t           if(data.profileimg == null) {\r\n");
      out.write("\t\t          \t  $(\"#profileimage\").attr(\"src\", \"/hifive/resources/profileUpfiles/profile.png\");\r\n");
      out.write("\t\t           }\r\n");
      out.write("\t\t           else {\r\n");
      out.write("\t\t          \t  $(\"#profileimage\").attr(\"src\", \"/hifive/resources/profileUpfiles/\"+data.profileimg);\r\n");
      out.write("\t\t           }\r\n");
      out.write("\t\t          //이름\r\n");
      out.write("\t\t          $(\"#name\").val(data.name);               \r\n");
      out.write("\t\t           //주소\r\n");
      out.write("\t\t           if(data.address == null){\r\n");
      out.write("\t\t              $(\"#sample5_address\").val(''); \r\n");
      out.write("\t\t           }else{\r\n");
      out.write("\t\t               $(\"#sample5_address\").val(data.address); \r\n");
      out.write("\t\t           }\r\n");
      out.write("\t\t       }\r\n");
      out.write("\t\t    }); // userajax\r\n");
      out.write("\t\t});\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("   <div class=\"container\">\r\n");
      out.write("      ");
      out.write("\r\n");
      out.write("\n");
      out.write("    \r\n");
      out.write("\t\r\n");
      out.write("\n");
 
   String userId = (String)session.getAttribute("userId");
   String userName = (String)session.getAttribute("userName");
   User headeruser = (User)session.getAttribute("loginuser");
   

      out.write("\r\n");
      out.write(" \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<meta name=\"viewport\"\r\n");
      out.write("\tcontent=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/hifive/resources/css/bootstrap.min.css\">\r\n");
      out.write("<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"/hifive/resources/image/index/logo2.png\" />\r\n");
      out.write("<title>Traveler's Couch</title>\r\n");
      out.write("\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\theader {\r\n");
      out.write("\t\tmargin: 5px;\r\n");
      out.write("\t\tpadding: 10px;\r\n");
      out.write("\t\twidth: 1000px;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.manubar {\r\n");
      out.write("\t\twidth: 300px;\r\n");
      out.write("\t\tposition: relative;\r\n");
      out.write("\t\tleft: 73%;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.manunav {\r\n");
      out.write("\t\twidth: 400px;\r\n");
      out.write("\t\tposition: relative;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.messagelist {\r\n");
      out.write("\t\twidth: 45px;\r\n");
      out.write("\t\theight: 40px; \r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.profileimg {\r\n");
      out.write("\t\twidth: 45px;\r\n");
      out.write("\t\theight: 45px;\r\n");
      out.write("\t}\t\r\n");
      out.write("\t\r\n");
      out.write("\t.dropdownMenuLink:hover {\r\n");
      out.write("\t\ttext-shadow:2px 2px #ff0000;\r\n");
      out.write("\t} \t\r\n");
      out.write("</style>\r\n");
      out.write("<script src=\"/hifive/resources/js/jquery-3.3.1.min.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tfunction callMyPage() {\r\n");
      out.write("\t\tvar userId = $(\"#userid\").val();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl : \"/hifive/info\",\r\n");
      out.write("\t\t\ttype : \"post\",\r\n");
      out.write("\t\t\tdata : {userid : userId},\r\n");
      out.write("\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\tsuccess : function(data){\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\tlocation.href = \"/hifive/views/user/mypage.jsp\";\r\n");
      out.write("\t\t\t}\t\t\t\r\n");
      out.write("\t\t}); //ajax\r\n");
      out.write("\t} //callMyPage\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<input type=\"hidden\" id=\"userid\" name=\"userid\" value=");
      out.print( userId );
      out.write(">\r\n");
      out.write("\t<script src=\"/hifive/resources/js/jquery-3.3.1.min.js\"></script>\r\n");
      out.write("\t<script\tsrc=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\"></script>\r\n");
      out.write("\t<script\tsrc=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"container1\">\r\n");
      out.write("\t\t<header>\r\n");
      out.write("\t\t\t<div id=\"logo\">\r\n");
      out.write("\t\t\t\t<a href=\"/hifive/main.jsp\"><img\r\n");
      out.write("\t\t\t\t\tsrc=\"/hifive/resources/image/logo.png\"\r\n");
      out.write("\t\t\t\t\tclass=\"rounded mx-auto d-block\" alt=\"로고\" title=\"메인 페이지로 이동\">\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"manubar\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<nav class=\"manunav\">\r\n");
      out.write("\t\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<th>");
      out.print( userName );
      out.write("님 &nbsp;&nbsp;</th>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<th><a href=\"/hifive/views/message/messageList.jsp\"> \r\n");
      out.write("\t\t\t\t\t\t\t<img id=\"msg\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tsrc=\"/hifive/resources/image/micon.jpg\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"messagelist\" title=\"요청받은 목록\">\r\n");
      out.write("\t\t\t\t\t\t\t</a>&nbsp;</th>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<th><a href=\"/hifive/profileinfo?userid=");
      out.print( userId );
      out.write("\"> \r\n");
      out.write("\t\t\t\t\t\t   <img class=\"profileimg rounded-circle\"\r\n");
      out.write("                  src=\"/hifive/resources/profileUpfiles/");
      out.print( headeruser.getProfile_image() );
      out.write("\" alt=\"프로필 사진\" title=\"프로필로 이동\">\r\n");
      out.write("                  \r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</a>&nbsp;</th>\r\n");
      out.write(" \r\n");
      out.write("\t\t\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"dropdown\" id=\"support\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<a class=\"btn dropdown-toggle p-3 mb-2 bg-white text-dark font-weight-bold\" href=\"#\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\trole=\"button\" id=\"dropdownMenuLink\" data-toggle=\"dropdown\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\taria-haspopup=\"true\" aria-expanded=\"false\"> menu </a>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuLink\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a class=\"dropdown-item\" href=\"/hifive/noticelist\">공지사항</a> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a class=\"dropdown-item\" href=\"/hifive/info?userid=");
      out.print( userId );
      out.write("\" onclick=\"callMyPage();\">마이페이지</a> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a class=\"dropdown-item\" href=\"/hifive/reportlist\">신고게시판</a>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a class=\"dropdown-item\" href=\"/hifive/views/support/tutorial.jsp\">튜토리얼</a> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a class=\"dropdown-item\" href=\"/hifive/views/support/safety.jsp\">안전유의사항</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a class=\"dropdown-item\" href=\"/hifive/logout\">로그아웃</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</nav>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t</header>\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("      <hr>\r\n");
      out.write("      <div id=\"main\">\r\n");
      out.write("         <div id=\"menu\">\r\n");
      out.write("      \t\t<div class=\"card\" style=\"width: 250px;\">            \r\n");
      out.write("               <font size=\"3\" ><b>Mypage</b></font>               \r\n");
      out.write("               <img class=\"card-img-top rounded-circle\" id=\"profileimage\" src=\"\" alt=\"Card image cap\" height=\"250px\">\r\n");
      out.write("               <div class=\"card-body\">\r\n");
      out.write("                  <p class=\"card-text\">                  \r\n");
      out.write("                  <div id=\"mpageInfo\" name=\"mpageInfo\" align=\"center\">\r\n");
      out.write("                    <div class=\"col-sm-10\"> \r\n");
      out.write("                       <input type=\"text\" readonly id=\"name\" class=\"form-control\" name=\"username\" style=\"width:100px; text-align:center;\">                       \r\n");
      out.write("                    </div> \r\n");
      out.write("                     <br>\r\n");
      out.write("                     <br>    \r\n");
      out.write("                     <form action=\"/hifive/pimage\" method=\"post\" enctype=\"multipart/form-data\">                              \r\n");
      out.write("                     <input type=\"file\" id=\"pimg\" name=\"pimg\" accept=\"image/*\">\r\n");
      out.write("                     <input type=\"hidden\" id=\"imguserid\" name=\"imguserid\" value=\"");
      out.print( headeruser.getUser_Id() );
      out.write("\">\r\n");
      out.write("                     <br>\r\n");
      out.write("                     <input type=\"submit\" class=\"btn btn-outline-dark text-dark\" id=\"imgbtn\" value=\"프사업로드\">\r\n");
      out.write("                     </form>        \r\n");
      out.write("                     <br>\r\n");
      out.write("                     <br>\r\n");
      out.write("                               \r\n");
      out.write("                  </div>\r\n");
      out.write("        \r\n");
      out.write("                  <br>\r\n");
      out.write("                  <div id=\"request\" name=\"request\" align=\"center\">\r\n");
      out.write("                     <table>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                           <th><input type=\"button\" class=\"btn btn-primary\" style=\"width:200px;\" value=\"선호하는 USER\"\r\n");
      out.write("                                 onclick=\"location.href='/hifive/views/favorite/favorite.jsp'\"></th>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                           <th><input type=\"button\" class=\"btn btn-primary\" style=\"width:200px;\" value=\"비밀번호 변경\"\r\n");
      out.write("                             \r\n");
      out.write("                                 onclick=\"location.href='/hifive/views/user/changePW.jsp'\"></th>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                     </table>\r\n");
      out.write("                     <br>\r\n");
      out.write("                        <form action=\"/hifive/userdelete?userid=");
      out.print(userId );
      out.write("\" method=\"post\">\r\n");
      out.write("                        <table>\r\n");
      out.write("                           <tr>\r\n");
      out.write("                               <th>                           \r\n");
      out.write("                                    <input type=\"submit\" class=\"btn btn-danger\" style=\"width:200px;\" value=\"회원 탈퇴\">                                    \r\n");
      out.write("                             </th>\r\n");
      out.write("                           </tr>\r\n");
      out.write("                        </table>\r\n");
      out.write("                      </form>                         \r\n");
      out.write("                  </div>                  \r\n");
      out.write("               </div>\r\n");
      out.write("            </div>              \r\n");
      out.write("         </div>\r\n");
      out.write("         \r\n");
      out.write("        <div id=\"content1\">\r\n");
      out.write("\r\n");
      out.write("        \r\n");
      out.write("\t\t</div>\r\n");
      out.write("               \r\n");
      out.write("      </div>\r\n");
      out.write("      <br>\r\n");
      out.write("      <hr>\r\n");
      out.write("      ");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n");
      out.write("\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"/hifive/resources/css/bootstrap.min.css\">\r\n");
      out.write("\t\r\n");
      out.write("\t<title></title>\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("      \r\n");
      out.write("      footer {\r\n");
      out.write("          margin: 20px;\r\n");
      out.write("          padding: 10px;\r\n");
      out.write("           width: 1000px;\r\n");
      out.write("         height : 70px;      \r\n");
      out.write("      }\r\n");
      out.write("      \r\n");
      out.write("     /*  #language {\r\n");
      out.write("         display : inline-block;\r\n");
      out.write("         left : 60%;\r\n");
      out.write("      }  */\r\n");
      out.write("   </style>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<script src=\"/hifive/resources/js/jquery-3.3.1.min.js\"></script>\r\n");
      out.write("\t<script\r\n");
      out.write("\t\tsrc=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\"></script>\r\n");
      out.write("\t<script\r\n");
      out.write("\t\tsrc=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t<footer>\r\n");
      out.write("\t\t2018- Copyright Hi-Five Inc. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t<button class=\"btn btn-secondary\" data-toggle=\"modal\"\r\n");
      out.write("\t\t\tdata-target=\"#accessTerms\" id=\"kk\">이용약관</button>&nbsp;&nbsp;\r\n");
      out.write("\t\t<button class=\"btn btn-secondary\" data-toggle=\"modal\"\r\n");
      out.write("\t\t\tdata-target=\"#policy\" id=\"kk\">정책</button>\r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- <div class=\"dropdown\" id=\"language\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<button class=\"btn btn-secondary btn-sm dropdown-toggle\"\r\n");
      out.write("\t\t\t\ttype=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\"\r\n");
      out.write("\t\t\t\taria-haspopup=\"true\" aria-expanded=\"false\">LANGUAGE</button>\r\n");
      out.write("\t\t\t<div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">\r\n");
      out.write("\t\t\t\t<a class=\"dropdown-item\" href=\"#\">KOREAN</a> <a\r\n");
      out.write("\t\t\t\t\tclass=\"dropdown-item\" href=\"#\">ENGLISH</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div> -->\r\n");
      out.write("\t</footer>\r\n");
      out.write("\r\n");
      out.write("\t<!-- 이용약관 Modal -->\r\n");
      out.write("\t<div class=\"modal fade\" id=\"accessTerms\" tabindex=\"-1\" role=\"dialog\"\r\n");
      out.write("\t\taria-labelledby=\"exampleModalLongTitle\" aria-hidden=\"true\">\r\n");
      out.write("\t\t<div class=\"modal-dialog\" role=\"document\">\r\n");
      out.write("\t\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t\t<h5 class=\"modal-title\" id=\"exampleModalLongTitle\">\r\n");
      out.write("\t\t\t\t\t\t<b>이용약관</b>\r\n");
      out.write("\t\t\t\t\t</h5>\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n");
      out.write("\t\t\t\t\t\taria-label=\"Close\">\r\n");
      out.write("\t\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<font style = \"color:#ff6633; font-size: 15pt\">\r\n");
      out.write("<b>본 이용 약관을주의 깊게 읽으십시오.</b></font><br> <br>\r\n");
      out.write("<font style = \"color:black; font-size: 11pt\">\r\n");
      out.write("<b>계정 등록 또는 서비스 액세스 또는 사용으로 귀하는 본 사용 약관 및 참조로 작성된 모든 조건을 준수함에 동의하게됩니다.</b></font><br><br>\r\n");
      out.write("<b>이 약관에 동의하지 않을 경우 서비스를 이용하지 마십시오.</b> <br><br>\r\n");
      out.write("<font style = \"color:black; font-size: 10pt\">\r\n");
      out.write("Hifive International, Inc.의 웹 사이트, 기타 온라인 또는 서비스 (총칭하여 \"서비스\")에 대한 귀하의 액세스 및 사용 ( \"Traveler's Couch\", \" 우리 \"또는\"우리 \"). 귀하가 어떤 단체를 대신하여 서비스를 사용하는 경우, 귀하는 귀하가 해당 단체를 대신하여 본 약관에 동의 할 권한이 있음을 진술하고 보증하며, 해당 단체는 귀하가 본 약관을 위반하는 것에 대해 책임을집니다. 본 약관에 따라 서비스를 사용하지 않으면 서비스 사용에 대한 귀하의 권리가 해지 또는 정지 될 수 있으며 Traveler's Couch 및 / 또는 심각한 민법 및 / 또는 형사 처벌로 법적 조치를받을 수 있습니다.\r\n");
      out.write("\r\n");
      out.write("우리는 언제든지 재량에 따라 본 약관 또는 서비스의 정책이나 지침을 변경하거나 수정할 권리가 있습니다. Traveler's Couch이 약관을 변경하는 경우,이 약관의 상단에 날짜를 수정하는 것을 포함하여 변경 통지를 제공합니다. 변경 또는 수정 사항을 게시 한 후에 서비스를 계속 사용하면 그러한 변경 또는 수정 사항을 수락하는 것으로 간주됩니다. 따라서 귀하는 서비스 사용에 적용되는 이용 약관을 이해하기 위해 본 약관 및 해당 정책 및 지침을 자주 검토해야합니다.\r\n");
      out.write("\r\n");
      out.write("Traveler's Couch이 회원에 관한 정보를 수집, 사용 및 공개하는 방법에 대한 정보는 개인 정보 보호 정책을 참조하십시오. 또한 서비스에 액세스하거나 사용함으로써 커뮤니티 지침을 준수하는 데 동의하게됩니다.\r\n");
      out.write("\r\n");
      out.write("이 약관과 관련하여 질문이 있으시면 연락주십시오. 이메일 trevelsfriend@gmail.com 으로 문의하실 수 있습니다.\r\n");
      out.write("</font>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 정책 Modal -->\r\n");
      out.write("\t<div class=\"modal fade\" id=\"policy\" tabindex=\"-1\" role=\"dialog\"\r\n");
      out.write("\t\taria-labelledby=\"exampleModalLongTitle\" aria-hidden=\"true\">\r\n");
      out.write("\t\t<div class=\"modal-dialog\" role=\"document\">\r\n");
      out.write("\t\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t\t<h5 class=\"modal-title\" id=\"exampleModalLongTitle\">\r\n");
      out.write("\t\t\t\t\t\t<b>정책</b>\r\n");
      out.write("\t\t\t\t\t</h5>\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n");
      out.write("\t\t\t\t\t\taria-label=\"Close\">\r\n");
      out.write("\t\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<font style = \"color:#ff6633; font-size: 15pt\">\r\n");
      out.write("<b>Traveler's Couch 정책</font><br><br>\r\n");
      out.write("<font style = \"color:#ff6633; font-size: 13pt\"><b>행동 정책</b></font><br><br>\r\n");
      out.write("<font style = \"color:black; font-size: 10pt\">\r\n");
      out.write("1. 스팸하지 마십시오 : 우리는 인간 상호 작용을 소중히 여기며 사이트의 콘텐츠를 원하고 구성원에게 개인화되고 가치있게 보내집니다.\r\n");
      out.write("동일한 메시지를 사이트, 회원 간 메시지, 소파 요청, 그룹, 지역 토론 또는 이벤트 목록에 복사하여 붙여 넣는 것은 허용되지 않습니다. \r\n");
      out.write("메시지 유형을 수신 할 의사가없는 많은 회원에게 보내는 메시지는 스팸으로 간주 될 수도 있습니다.\r\n");
      out.write("<br>\r\n");
      out.write("2. 날짜를 찾지 마라. 우리 멤버들은 Traveler's Couch에 가입하여 우정을 쌓는다.\r\n");
      out.write("데이트를 위해 다른 회원들에게 연락하지 마시고, 성 파트너를 찾으려면 다른 사이트를 이용하십시오.\r\n");
      out.write("Google은 온라인 및 오프라인에서 원치 않는 성적인 진보에 관한 보고서를 진지하게 받아들입니다.\r\n");
      out.write("이러한 진술은 Google의 행동 강령을 위반 한 것으로 간주 될 수 있습니다. \r\n");
      out.write("여기에는 비 호스팅 / 서핑 관련 문제 및 성적 암시 행동에 대해 다른 회원들과 연락하려는 체계적인 시도가 포함될 수 있습니다. \r\n");
      out.write("타인의 경계를 존중하십시오. 다른 Traveler's Couch가 불편 함을 느끼면 자신의 감정을 존중하고 한 발 뒤로 물러나십시오.\r\n");
      out.write("<br>\r\n");
      out.write("3. 귀하의 소파를 청구하지 마십시오 : 우리 지역 사회는 친절한 환대를 제공합니다. \r\n");
      out.write("귀하의 소파와 교환하기 위해 돈이나 노동력을 요구하거나 회원에게 유료 숙박 서비스를 추천하는 것은 허용되지 않습니다.\r\n");
      out.write("<br>\r\n");
      out.write("4. 협박, 스토킹 또는 괴롭힘 : 스토킹, 협박, 위협 및 다른 회원의 괴롭힘은 금지됩니다. \r\n");
      out.write("괴롭힘은 대상자에게 불리한 영향을 미칠 목적으로 보이는 공격 행동의 패턴으로 정의됩니다. \r\n");
      out.write("괴롭힘의 예로는 위협 만들기, 사람과 반복적으로 원하지 않는 접촉, 다른 사람의 개인 정보 게시 등이 있습니다. \r\n");
      out.write(" Traveler's Couch은 미래의 호스트, 서퍼 또는  Traveler's Couch 커뮤니티에 위협이 될 수 있다고 생각하는 프로필에 대해 조치를 취할 권리를 보유합니다 (위 참조).\r\n");
      out.write("<br>\r\n");
      out.write("5. 하나의 프로파일 만 작성 : 중복, 위조 및 농담 프로파일은 허용되지 않습니다. \r\n");
      out.write("처음으로 만드는 프로필은 나와 있어야하며 가질 수있는 유일한 프로필입니다. \r\n");
      out.write("우리의 신뢰 네트워크는 모든 사람이 자신의 명성을 지킬 것을 요구합니다.\r\n");
      out.write("<br>\r\n");
      out.write("6. 너 자신 있으 십시오 : 너 자신을 다른 사람으로 잘못 표시하는 것은 금지되어 있습니다. \r\n");
      out.write("여기에는 대리인, 대리인, 직원 또는  Traveler's Couch의 제휴사로서의 대리인이 포함됩니다.\r\n");
      out.write("<br>\r\n");
      out.write("7. 법을 준수하십시오 : 불법 활동에 관여하거나 장려하지 마십시오. 관련 법률이나 규정을 위반하지 마십시오.\r\n");
      out.write("<br>\r\n");
      out.write("8.  Traveler's Couch을 올바르게 사용하십시오 : 다른 멤버가 사이트를 완전히 즐기지 못하게하거나 사이트의 기능을 해칠 수있는 방식으로 \r\n");
      out.write("Traveler's Couch을 사용하는 것은 금지됩니다. 여기에는 과도한보고, 플래그 또는 다른 구성원의 블록을 유발하는 동작이 포함됩니다. \r\n");
      out.write("우리는  Traveler's Couch 회원들이 신고 한 통신에 대한 적극적인 홍보 활동을 금지하거나 다른  Traveler's Couch에 대한 경험을 해할 것이라고 믿습니다.\r\n");
      out.write("또한 사이트에 바이러스, 손상된 데이터 또는 기타 잠재적으로 유해한 코드가 포함 된 것을 게시하십시오.  \r\n");
      out.write("Traveler's Couch 시스템을 우회하거나 의도를 저해하는 방식으로 이러한 시스템을 사용하려고 시도하는 것은 금지됩니다.\r\n");
      out.write("<br>\r\n");
      out.write("9. 다른 사람들에게 당신의 믿음과 생활 방식 선택을 강요하지 마십시오. \r\n");
      out.write("카우치 서핑은 회원들의 다양성을 자랑스럽게 생각합니다. \r\n");
      out.write("우리의 플랫폼을 사용하여 귀하의 라이프 스타일이나 신념 체계에 동참하기 위해 다른 회원을 모집하는 것은 우리의 행동 강령을 위반하는 것일 수 있습니다. \r\n");
      out.write("귀하의 라이프 스타일 또는 신념 체계의 채택을 귀하의 소파 제공 조건으로 삼지 마십시오. \r\n");
      out.write("특정 주택 규칙이 기대되는 반면, 서퍼에게 부과 된 조건이 위험을 초래하거나 회원 경험을 해치거나  Traveler's Couch의 평판을 손상한다고 판단되면 우리는 요구 사항을 금지 할 권리가 있습니다.\r\n");
      out.write("</font>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("   </div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
