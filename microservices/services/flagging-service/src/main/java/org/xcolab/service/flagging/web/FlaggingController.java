package org.xcolab.service.flagging.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.service.flagging.service.FlaggingService;

@RestController
public class FlaggingController {

    @Autowired
    private FlaggingService flaggingService;


}
