package com.votifysoft.app.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.votifysoft.app.beans.ElectiveBeanI;
import com.votifysoft.app.beans.NominationBeanI;
import com.votifysoft.app.beans.UserBeanI;
import com.votifysoft.app.utils.FileUploadUtils;
import com.votifysoft.model.entity.Electives;
import com.votifysoft.model.entity.Nominees;
import com.votifysoft.model.entity.User;

@WebServlet("/nominations")
@MultipartConfig(location = "/home/chief/Votify-v2/src/main/webapp/app/artifacts", maxFileSize = 1024 * 1024 * 8, // 1 MB
        maxRequestSize = 1024 * 1024 * 10, // 10 MB
        fileSizeThreshold = 1024 * 1024 // 1 MB
)
public class NominationsCreatorAction extends BaseAction {

    @EJB
    ElectiveBeanI eBeanI;

    @EJB
    NominationBeanI nBeanI;

    @EJB
    UserBeanI userBeanI;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setAttribute("content", "<h1>This is the NominationsCreator content.</h1>");
        req.getRequestDispatcher("app/NominationsCreator.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Map<String, String[]> paramMap = req.getParameterMap();

            List<String[]> choiceValues = paramMap.entrySet().stream()
                    .filter(entry -> entry.getKey().contains("nominee"))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());

            Map<String, String[]> electiveNameParameters = paramMap.entrySet().stream()
                    .filter(entry -> entry.getKey().equals("electiveTitle") || entry.getKey().equals("Deadline"))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            // Process file field (photo upload)
            List<String> photoPaths = new ArrayList<>();
            for (Part part : req.getParts()) {
                // Check if the part represents a file by examining the submitted file name
                String imageName = FileUploadUtils.getFileName(part);
                if (!imageName.isEmpty()) {
                    String photoPath = "/home/chief/Votify-v2/src/main/webapp/app/artifacts/" + imageName;
            
                    System.out.println("this is the image name " + imageName);
                    part.write(photoPath);

                    //save the relative path for easier retrieval

                    photoPaths.add(imageName);
                }
            }
            if (photoPaths.isEmpty()) {
                System.out.println("PhotoPaths is empty!!!!");
            } else {
                for (String stringPath : photoPaths) {
                    System.out.println("This is the string path==>>>   --" + stringPath);
                }
            }

            Electives elective = serializeForm(Electives.class, electiveNameParameters);
            User creator = userBeanI.getUserById((int) req.getSession(false).getAttribute("userId"));
            elective.setCreator(creator);

            if (elective != null && (elective.getElectiveTitle().isBlank() || elective.getElectiveTitle().isEmpty())) {
                System.out.println("No elective title exists!!!");
            }
            eBeanI.registerElective(elective);

            Electives latestElective = eBeanI.getLatestElective();

            List<Nominees> nomineeList = serializeNominees(choiceValues);

            for (Nominees iterable_element : nomineeList) {
                System.out.println("Take noti of this====>>>>" + iterable_element.toString());
            }

            // Register nominees with their photos
            nBeanI.registerNominee(latestElective, nomineeList, photoPaths);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        resp.sendRedirect("./home");
    }

}
