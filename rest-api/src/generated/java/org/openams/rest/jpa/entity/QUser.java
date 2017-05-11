package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1755391932L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.util.Date> accountExpireDtt = createDateTime("accountExpireDtt", java.util.Date.class);

    public final DateTimePath<java.util.Date> credentialsExpireDtt = createDateTime("credentialsExpireDtt", java.util.Date.class);

    public final StringPath email = createString("email");

    public final DateTimePath<java.util.Date> lastAccessDtt = createDateTime("lastAccessDtt", java.util.Date.class);

    public final StringPath password = createString("password");

    public final StringPath passwordSalt = createString("passwordSalt");

    public final ListPath<Role, QRole> roles = this.<Role, QRole>createList("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final EnumPath<org.openams.rest.jpa.entity.enums.UserStatus> status = createEnum("status", org.openams.rest.jpa.entity.enums.UserStatus.class);

    public final StringPath userName = createString("userName");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

