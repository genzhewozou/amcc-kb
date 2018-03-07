package com.nroad.amcc.kb;

import com.nroad.amcc.PlatformError;
import com.nroad.amcc.PlatformException;

public class KnowledgeBaseException extends PlatformException {
    public KnowledgeBaseException(PlatformError error) {
        super(error,error.getDescription()+":"+error.getCode());
    }

}
