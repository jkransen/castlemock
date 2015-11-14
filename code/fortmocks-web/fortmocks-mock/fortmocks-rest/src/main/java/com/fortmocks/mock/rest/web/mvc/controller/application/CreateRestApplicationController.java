/*
 * Copyright 2015 Karl Dahlgren
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fortmocks.mock.rest.web.mvc.controller.application;

import com.fortmocks.mock.rest.model.project.service.message.input.CreateRestApplicationInput;
import com.fortmocks.mock.rest.model.project.service.message.output.CreateRestApplicationOutput;
import com.fortmocks.mock.rest.web.mvc.command.application.CreateRestApplicationCommand;
import com.fortmocks.mock.rest.web.mvc.controller.AbstractRestViewController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The project controller provides functionality to retrieve a specific project
 * @author Karl Dahlgren
 * @since 1.0
 */
@Controller
@RequestMapping("/web/rest/project")
public class CreateRestApplicationController extends AbstractRestViewController {

    private static final String PAGE = "mock/rest/application/createRestApplication";


    @PreAuthorize("hasAuthority('MODIFIER') or hasAuthority('ADMIN')")
    @RequestMapping(value = "/{projectId}/create/application", method = RequestMethod.GET)
    public ModelAndView defaultPage(@PathVariable final Long projectId) {
        final ModelAndView model = createPartialModelAndView(PAGE);
        model.addObject(REST_PROJECT_ID, projectId);
        model.addObject(COMMAND, new CreateRestApplicationCommand());
        return model;
    }


    @PreAuthorize("hasAuthority('MODIFIER') or hasAuthority('ADMIN')")
    @RequestMapping(value = "/{projectId}/create/application", method = RequestMethod.POST)
    public ModelAndView createProject(@PathVariable final Long projectId, @ModelAttribute final CreateRestApplicationCommand createRestApplicationCommand) {
        final CreateRestApplicationOutput output = serviceProcessor.process(new CreateRestApplicationInput(projectId, createRestApplicationCommand.getRestApplication()));
        return redirect("/rest/project/" + projectId + "/application/" + output.getSavedRestApplication().getId());
    }

}

