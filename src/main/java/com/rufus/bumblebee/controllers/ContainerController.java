package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.containers.TestDataContainerRequest;
import com.rufus.bumblebee.controllers.requests.tests.BaseRequest;
import com.rufus.bumblebee.controllers.responses.BaseResponse;
import com.rufus.bumblebee.services.ContainerService;
import com.rufus.bumblebee.tables.Container;
import com.rufus.bumblebee.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/container_manager")
public class ContainerController extends BaseController {

    private final ContainerService service;

    @Autowired
    public ContainerController(ContainerService service) {
        this.service = service;
    }

    @RequestMapping(path = "/add_container", method = RequestMethod.GET)
    public @ResponseBody
    BaseResponse<Container> setTestDataContainer(@RequestBody TestDataContainerRequest request) {
        BaseResponse<Container> response = new BaseResponse<>();
        try {
            ValidatorUtils.validate(request);
            Container container = service.createTestDataContainer(request);
            response.setResponse(container);
            return response;
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
    }

    @RequestMapping(path = "/remove_container", method = RequestMethod.GET)
    public @ResponseBody
    BaseResponse<Container> setTestDataContainer(@RequestBody BaseRequest request) {
        BaseResponse<Container> response = new BaseResponse<>();
        try {
            ValidatorUtils.validate(request);
            service.removeContainer(request.getContainerId());
            return response;
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
    }

}
