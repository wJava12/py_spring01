package com.java.mediapp.controller;


import com.java.mediapp.model.Paciente;
import com.java.mediapp.util.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


import static com.java.mediapp.util.Util.ENGLISH;
import static com.java.mediapp.util.Util.FRANCE;

@RestController
@RequestMapping("/utils")
public class UtilController {

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @GetMapping("/locale/{loc}")
    public ResponseEntity<Void> changeLocale(@PathVariable("loc") String loc){
        Locale userLocal= null;

        switch (loc){
            case ENGLISH:userLocal=Locale.ENGLISH; break;
            case FRANCE:userLocal=Locale.FRANCE; break;
            default:userLocal=Locale.ROOT;break;
        }

        localeResolver.setLocale(httpServletRequest,httpServletResponse,userLocal);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
