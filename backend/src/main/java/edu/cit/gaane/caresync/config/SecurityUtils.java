package edu.cit.gaane.caresync.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import edu.cit.gaane.caresync.features.authentication.entity.UserEntity;


public class SecurityUtils {



    public static UserEntity getCurrentUser(){


        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();



        if(authentication == null){

            return null;

        }



        Object principal =
                authentication.getPrincipal();



        if(principal instanceof UserEntity){

            return (UserEntity) principal;

        }



        return null;

    }

}