/*
 * Copyright 2021 StreamEx contributors
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
/**
 * This library provides enhancements for Java 8 Stream API.
 * See package documentation for more information.
 */
module one.util.streamex {
  requires java.base;
  exports one.util.streamex;
  // No changes needed for Java 21 compatibility based on the provided instructions.
  // The module exports its public API package 'one.util.streamex'.
  // If specific parts of the application or dependent libraries require
  // deep reflective access to the internals of this package (which is generally
  // discouraged), an 'opens one.util.streamex;' directive could be added here,
  // or '--add-opens one.util.streamex=ALL-UNNAMED' could be used as a command-line
  // argument at runtime. However, without a specific requirement, exporting
  // the package is sufficient for standard usage and maintains strong encapsulation.
}