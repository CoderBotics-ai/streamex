/*
 * Copyright 2015, 2024 StreamEx contributors
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

package one.util.streamex;

/**
 * @author Tagir Valeev
 */
/* package */ interface VerSpec {
   // Java 21 Compatibility Check:
   // This interface and its initialization of VersionSpecific remain compatible with Java 21.
   // The underlying implementation (Java9Specific) needs separate verification,
   // but the structure here requires no changes for the Java 17 to 21 upgrade.
   VersionSpecific VER_SPEC = new Java9Specific();
}