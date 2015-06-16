package com.lazywell.android.puydufou.entities.persistent;

import com.slimgears.slimrepo.core.annotations.GenerateRepository;
import com.slimgears.slimrepo.core.interfaces.Repository;
import com.slimgears.slimrepo.core.interfaces.entities.EntitySet;

/**
 * Created by victor on 16/06/2015.
 */
@GenerateRepository(version = 1, name = "show_db")
public interface ShowRepository extends Repository {
    EntitySet<ShowEntity> shows();
    EntitySet<CoordinatesEntity> coordinates();
}
