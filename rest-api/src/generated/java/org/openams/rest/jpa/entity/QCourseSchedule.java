package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourseSchedule is a Querydsl query type for CourseSchedule
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCourseSchedule extends EntityPathBase<CourseSchedule> {

    private static final long serialVersionUID = -215681781L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourseSchedule courseSchedule = new QCourseSchedule("courseSchedule");

    public final QCourse course;

    public final StringPath desc = createString("desc");

    public final DatePath<java.util.Date> endDt = createDate("endDt", java.util.Date.class);

    public final TimePath<java.sql.Time> endT = createTime("endT", java.sql.Time.class);

    public final StringPath id = createString("id");

    public final StringPath location = createString("location");

    public final DateTimePath<java.util.Date> modifiedDtt = createDateTime("modifiedDtt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final QStaff staff;

    public final DatePath<java.util.Date> startDt = createDate("startDt", java.util.Date.class);

    public final TimePath<java.sql.Time> startT = createTime("startT", java.sql.Time.class);

    public final EnumPath<org.openams.rest.jpa.entity.enums.CourseScheduleStatus> status = createEnum("status", org.openams.rest.jpa.entity.enums.CourseScheduleStatus.class);

    public final StringPath term = createString("term");

    public QCourseSchedule(String variable) {
        this(CourseSchedule.class, forVariable(variable), INITS);
    }

    public QCourseSchedule(Path<? extends CourseSchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCourseSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCourseSchedule(PathMetadata metadata, PathInits inits) {
        this(CourseSchedule.class, metadata, inits);
    }

    public QCourseSchedule(Class<? extends CourseSchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new QCourse(forProperty("course")) : null;
        this.staff = inits.isInitialized("staff") ? new QStaff(forProperty("staff"), inits.get("staff")) : null;
    }

}

