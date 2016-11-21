package ar.com.clinicasmanager;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.support.BindingAwareModelMap;

import ar.com.clinicasmanager.exception.LockNotGrantedException;
import ar.com.clinicasmanager.locking.ConsultaLockingManager;

@Aspect
public class ReadOnlyIfLockedAspect {
	
	@Autowired
	private ConsultaLockingManager lockingManager;

	@After("execution(@ar.com.clinicasmanager.annotation.ReadOnlyIfLocked String ar.com.clinicasmanager.controller.*Controller.*(..))")
	public void doAfter(JoinPoint joinPoint){
		
		Long id = null;
		BindingAwareModelMap model = null; 
		Object[] args = joinPoint.getArgs();
		
		for (Object object : args) {
			if(object.getClass().equals(Long.class)){
				id = (Long) object; 
			}
			else if(object.getClass().equals(BindingAwareModelMap.class)){
				model = (BindingAwareModelMap) object;
			}
		}
		
		try {
			lockingManager.getLockOnConsulta(id);
		} catch (LockNotGrantedException e) {
			model.addAttribute("readOnly", true);
		}
	}
}
