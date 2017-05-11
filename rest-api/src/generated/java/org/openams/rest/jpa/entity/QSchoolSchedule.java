package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchoolSchedule is a Querydsl query type for SchoolSchedule
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSchoolSchedule extends EntityPathBase<SchoolSchedule> {

    private static final long serialVersionUID = -52230300L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchoolSchedule schoolSchedule = new QSchoolSchedule("schoolSchedule");

    public final DateTimePath<java.util.Date> endDtt = createDateTime("endDtt", java.util.Date.class);

    public final StringPath eventName = createString("eventName");

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> modifiedDtt = createDateTime("modifiedDtt", java.util.Date.class);

    public final QStaff staff;

    public final DateTimePath<java.util.Date> startDtt = createDateTime("startDtt", java.util.Date.class);

    public final NumberPath<Byte> status = createNumber("status", Byte.class);

    public QSchoolSchedule(String variable) {
        this(SchoolSchedule.class, forVariable(variable), INITS);
    }

    public QSchoolSchedule(Path<? extends SchoolSchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSchoolSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSchoolSchedule(PathMetadata metadata, PathInits inits) {
        this(SchoolSchedule.class, metadata, inits);
    }

    public QSchoolSchedule(Class<? extends SchoolSchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.staff = inits.isInitialized("staff") ? new QStaff(forProperty("staff"), inits.get("staff")) : null;
    }

}

