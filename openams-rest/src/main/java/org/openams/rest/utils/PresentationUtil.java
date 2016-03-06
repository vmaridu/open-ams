package org.openams.rest.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.openams.rest.jpa.entity.Role;
import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.jpa.entity.User;

//FIXME:Replace it with Dozer ; Asses Reflection Overhead
public class PresentationUtil {

	public static enum SETTER_MODE{EXCLUDE,INCLUDE};

	public static void setMany(Object src, Object dst, SETTER_MODE mode, String... properties) {
		setMany(src, dst, mode, Arrays.stream(properties).collect(Collectors.toSet()));
	}

	// TODO:Consider Parameter Overloading, Inheritance
	public static void setMany(Object src, Object dst, SETTER_MODE mode, Set<String> properties) {
		// return if source and destination types doesn't match
		if ( !src.getClass().equals(dst.getClass())    ) {
			return;
		}
		// get all setter methods of destination object
		Arrays.stream(dst.getClass().getMethods())
		// check if its a setter method
		.filter(dm -> dm.getName().contains("set"))
		.filter(dm -> allow(dm,properties,mode))
		.forEach( dm -> {
			try {
				Method getMethod = src.getClass() .getMethod( dm.getName().replaceFirst("set", "get"));
				dm.invoke(dst, getMethod.invoke(src));
			} catch (Exception e) {}
		});
	}

	public static boolean allow(Method method, Set<String> properties, SETTER_MODE mode){
		if(mode == SETTER_MODE.EXCLUDE){
			return properties.stream().noneMatch(p -> method.getName().toUpperCase().contains(p.toUpperCase()));
		}else{
			return properties.stream().anyMatch(p -> method.getName().toUpperCase().contains(p.toUpperCase()));
		}
	}

	public static Collection<Staff> getPresentableSatffs(Collection<Staff> 	staffs) {
		return staffs.stream().map(PresentationUtil :: getPresentableSatff).collect(Collectors.toList());
	}

	public static Staff getPresentableSatff(Staff staff) {
		Staff result = new Staff();
		setMany(staff,result,SETTER_MODE.EXCLUDE,"user","testScores","courseSchedules");
		result.setUser(getPresentableUser(staff.getUser()));
		return result;
	}


	public static User getPresentableUser(User user) {
		User result = new User();
		setMany(user,result,SETTER_MODE.EXCLUDE,"roles");
		user.setRoles(user.getRoles().stream().map(PresentationUtil :: getPresentableRole).collect(Collectors.toList()));
		return result;
	}


	public static Role getPresentableRole(Role role) {
		Role result = new Role();
		setMany(role,result,SETTER_MODE.EXCLUDE,"users");
		return result;
	}
}
