package member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String, Command> commandMap = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        // Command 초기화
    	commandMap.put("/memberLogin.do", new MemberLoginCommand());
    	commandMap.put("/memberMain.do", new MemberMainCommand());
    	commandMap.put("/memberPWCheck.do", new MemberPWCheckCommand());
    	commandMap.put("/memberEditRequest.do", new MemberEditRequestCommand());
    	commandMap.put("/memberEdit.do", new MemberEditCommand());
    	commandMap.put("/memberDelete.do", new MemberDeleteCommand());
    	commandMap.put("/SearchIDPW.do", new MemberSearchIDPWCommand());
    	commandMap.put("/memberJoin.do", new MemberJoinCommand());
    	commandMap.put("/memberDoubleCheck.do", new MemberDoubleCheckCommand());
    	commandMap.put("/memberJoinBuffer.do", new MemberJoinBuffer());
    	commandMap.put("/memberOut.do", new MemberOutCommand());
    	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
    	String uri = request.getRequestURI();
        String contextPath = request.getContextPath()+"/member";
        String commandKey = uri.substring(contextPath.length());
        Command command = commandMap.get(commandKey);
        
        if (command != null) {
            String viewPage = "/member"+command.execute(request, response);
            if (viewPage.equals("/member/main/main.jsp")) {
            	viewPage = "/main/main.jsp";
            }
        	if (!viewPage.equals("/membernull")) {
                request.getRequestDispatcher(viewPage).forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}