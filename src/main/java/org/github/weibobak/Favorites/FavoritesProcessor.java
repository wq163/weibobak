package org.github.weibobak.Favorites;

import weibo4j.model.Favorites;


/**
 * 
 * @author 无花
 * @since 2012-6-6 下午12:25:48
 */

public interface FavoritesProcessor {

    boolean process(Favorites favorites);
}
