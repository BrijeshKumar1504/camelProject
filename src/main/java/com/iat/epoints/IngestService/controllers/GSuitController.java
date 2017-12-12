package com.iat.epoints.IngestService.controllers;

import com.google.api.services.admin.directory.Directory;
import com.google.api.services.admin.directory.model.User;
import com.google.api.services.admin.directory.model.Users;
import com.iat.epoints.IngestService.services.GSuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/gsuite/org_name")
class GSuiteController {

    //@Autowired
   //private GSuitService suitService;

    /*
     * throws IOException
     */
    @RequestMapping(value = { "/pullusers", "/pulldepartments" }, method = RequestMethod.GET)
    public @ResponseBody List<String> pullUsersFromGSuite(HttpServletRequest request) {

        String url = request.getServletPath();

        List<String> list = new ArrayList<String>();

        try {
            // Build a new authorized API client service.
            Directory service = GSuitService.getDirectoryService();

            // Print the first 10 users in the domain.
            Users result = service.users().list().setCustomer("my_customer").setMaxResults(100).setOrderBy("email")
                    .execute();
            List<User> users = result.getUsers();

            if (users == null || users.size() == 0) {
                System.out.println("No User Data Found");
                //throw new("No User Data Found");
            } else {
                if (url.equals("/gsuite/org_name/pullusers")) {
                    for (User user : users) {

                      // System.out.println("Users <> :" + user.getName().getFullName() + ": " + user.getPrimaryEmail());
                        //list.add(user.getName().getFullName());
                       // list.add(user);


                    }
                } //else if (url.equals("/gsuite/org_name/pulldepartments")) {
                    //for (User user : users) {
                      //  System.out.println("Departments <> :" + user.getOrgUnitPath().replaceAll("\\/", ""));
                      //  list.add(user.getOrgUnitPath().replaceAll("\\/", ""));
                    //}
                //}
            }
        } catch (IOException e) {
            try {
                throw e;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return list;
    }
}