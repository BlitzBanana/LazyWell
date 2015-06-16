package com.lazywell.android.puydufou.entities.persistent;

import com.slimgears.slimrepo.core.annotations.GenerateEntity;

import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
@GenerateEntity
public class AbstractCustomScheduleEntity {
    protected int id;
    protected String name;
    protected List<AbstractShowEntity> shows;
}
