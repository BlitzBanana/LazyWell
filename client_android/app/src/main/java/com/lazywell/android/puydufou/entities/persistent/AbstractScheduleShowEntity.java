package com.lazywell.android.puydufou.entities.persistent;

import com.slimgears.slimrepo.core.annotations.GenerateEntity;

/**
 * Created by victor on 16/06/2015.
 */
@GenerateEntity
public class AbstractScheduleShowEntity {
    protected int id;
    protected AbstractShowEntity show;
    protected AbstractCustomScheduleEntity schedule;
}
