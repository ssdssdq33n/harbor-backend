package com.hungnv.backend.common.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {
    private final AuditService auditService;

    public AuditAspect(AuditService auditService) {
        this.auditService = auditService;
    }

    @AfterReturning("within(@org.springframework.web.bind.annotation.RestController *) && within(com.hungnv.backend.modules..controller..*)")
    public void afterController(JoinPoint joinPoint) {
        String action = joinPoint.getSignature().toShortString();
        auditService.log(action, null);
    }
}

