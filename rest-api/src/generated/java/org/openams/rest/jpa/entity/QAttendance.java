package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendance is a Querydsl query type for Attendance
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAttendance extends EntityPathBase<Attendance> {

    private static final long serialVersionUID = 242274466L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttendance attendance = new QAttendance("attendance");

    public final QAttendanceBy attendanceBy;

    public final StringPath comment = createString("comment");

    public final StringPath id = createString("id");

    public final EnumPath<org.openams.rest.jpa.entity.enums.AttendanceStatus> status = createEnum("status", org.openams.rest.jpa.entity.enums.AttendanceStatus.class);

    public final QStudentCourseEnrollment studentCourseEnrollment;

    public QAttendance(String variable) {
        this(Attendance.class, forVariable(variable), INITS);
    }

    public QAttendance(Path<? extends Attendance> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttendance(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttendance(PathMetadata metadata, PathInits inits) {
        this(Attendance.class, metadata, inits);
    }

    public QAttendance(Class<? extends Attendance> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attendanceBy = inits.isInitialized("attendanceBy") ? new QAttendanceBy(forProperty("attendanceBy"), inits.get("attendanceBy")) : null;
        this.studentCourseEnrollment = inits.isInitialized("studentCourseEnrollment") ? new QStudentCourseEnrollment(forProperty("studentCourseEnrollment"), inits.get("studentCourseEnrollment")) : null;
    }

}

