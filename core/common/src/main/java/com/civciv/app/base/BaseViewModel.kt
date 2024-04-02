/*
 * Copyright 2024 Fatih OZTURK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.civciv.app.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State, Event> : ViewModel() {
    private val initialState: State by lazy { createInitialState() }

    abstract fun createInitialState(): State

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    abstract fun event(event: Event)

    private val handledOneTimeEvents = mutableSetOf<Event>()

    protected fun setState(reducer: State.() -> State) {
        _state.update(reducer)
    }

    protected fun handleOneTimeEvent(event: Event, block: () -> Unit) {
        if (event !in handledOneTimeEvents) {
            handledOneTimeEvents.add(event)
            block()
        }
    }
}
