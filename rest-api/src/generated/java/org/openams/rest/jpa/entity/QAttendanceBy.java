package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendanceBy is a Querydsl query type for AttendanceBy
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAttendanceBy extends EntityPathBase<AttendanceBy> {

    private static final long serialVersionUID = 897530009L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttendanceBy attendanceBy = new QAttendanceBy("attendanceBy");

    public final ListPath<Attendance, QAttendance> attendances = this.<Attendance, QAttendance>createList("attendances", Attendance.class, QAttendance.class, PathInits.DIRECT2);

    public final StringPath comment = createString("comment");

    public final QCourseSchedule courseSchedule;

    public final StringPath id = createString("id");

    public final QStaff staff;

    public final DateTimePath<java.util.Date> takenDtt = createDateTime("takenDtt", java.util.Date.class);

    public QAttendanceBy(String variable) {
        this(AttendanceBy.class, forVariable(variable), INITS);
    }

    public QAttendanceBy(Path<? extends AttendanceBy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttendanceBy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttendanceBy(PathMetadata metadata, PathInits inits) {
        this(AttendanceBy.class, metadata, inits);
    }

    public QAttendanceBy(Class<? extends AttendanceBy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.courseSchedule = inits.isInitialized("courseSchedule") ? new QCourseSchedule(forProperty("courseSchedule"), inits.get("courseSchedule")) : null;
        this.staff = inits.isInitialized("staff") ? new QStaff(forProperty("staff"), inits.get("staff")) : null;
    }

}

