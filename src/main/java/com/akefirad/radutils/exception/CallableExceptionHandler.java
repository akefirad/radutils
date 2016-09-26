/*
 * Copyright © 2016 Rad Akefirad rad@akefirad.com
 *
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */

package com.akefirad.radutils.exception;

@FunctionalInterface
public interface CallableExceptionHandler<R> {
    R handle (Exception exception);
}
