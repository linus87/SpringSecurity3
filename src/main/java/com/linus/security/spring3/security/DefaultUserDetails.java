package com.linus.security.spring3.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class DefaultUserDetails extends HashMap<String, Object> implements UserDetails {

	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -837150249176468080L;
	
	//~ Instance fields ================================================================================================

    private final Set<GrantedAuthority> authorities;  
    
	public DefaultUserDetails (String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		if (((username == null) || "".equals(username)) || (password == null)) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
       
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        
        this.put("username", username);
        this.put("password", password);
        this.put("enabled", enabled);
        this.put("accountNonExpired", accountNonExpired);
        this.put("credentialsNonExpired", credentialsNonExpired);
        this.put("accountNonLocked", accountNonLocked);
        this.put("authorities", this.authorities);
	}
	
	public DefaultUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this(username, password, true, true, true, true, authorities);
	}	

	public boolean isAgencyMode() {
		boolean agencyMode = false;
		if (this.get("agencyMode") != null) {
			agencyMode = (boolean)this.get("agencyMode");
		}
		return agencyMode;
	}

	public void setAgencyMode(boolean agencyMode) {
		this.put("agencyMode", agencyMode);
	}

	public String getAmLevel() {
		return (String)this.get("amLevel");
	}

	public void setAmLevel(String amLevel) {
		this.put("amLevel", amLevel);
	}

	public String getFullName() {
		return (String)this.get("fullName");
	}

	public void setFullName(String fullName) {
		this.put("fullName", fullName);
	}

	public String[] getRoles() {
		return (String[])this.get("roles");
	}

	public void setRoles(String[] roles) {
		this.put("roles", roles);
	}

	/*has value only in agency mode. It's NT account*/
	public String getPrincipal() {
		return (String)this.get("principal");
	}

	public void setPrincipal(String principal) {
		this.put("principal", principal);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>)this.get("authorities");
	}

	@Override
	public String getPassword() {
		return (String)this.get("password");
	}

	@Override
	public String getUsername() {
		return (String)this.get("username");
	}

	@Override
	public boolean isAccountNonExpired() {
		boolean accountNonExpired = false;
		if (this.get("accountNonExpired") != null) {
			accountNonExpired = (boolean)this.get("accountNonExpired");
		}
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		boolean accountNonLocked = false;
		if (this.get("accountNonLocked") != null) {
			accountNonLocked = (boolean)this.get("accountNonLocked");
		}
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		boolean credentialsNonExpired = false;
		if (this.get("credentialsNonExpired") != null) {
			credentialsNonExpired = (boolean)this.get("credentialsNonExpired");
		}
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		boolean enabled = false;
		if (this.get("enabled") != null) {
			enabled = (boolean)this.get("enabled");
		}
		return enabled;
	}
	
    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities =
            new TreeSet<GrantedAuthority>(new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }
    
    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to the set.
            // If the authority is null, it is a custom authority and should precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

}
