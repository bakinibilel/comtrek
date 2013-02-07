/*******************************************************************************
 * Copyright (c) 2013 ComTrek.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     ComTrek - initial API and implementation
 ******************************************************************************/
package comtrek

import org.springframework.dao.DataIntegrityViolationException

class TrekController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [trekInstanceList: Trek.list(params), trekInstanceTotal: Trek.count()]
    }

    def create() {
        [trekInstance: new Trek(params)]
    }

    def save() {
        def trekInstance = new Trek(params)
        if (!trekInstance.save(flush: true)) {
            render(view: "create", model: [trekInstance: trekInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'trek.label', default: 'Trek'), trekInstance.id])
        redirect(action: "show", id: trekInstance.id)
    }

    def show(Long id) {
        def trekInstance = Trek.get(id)
        if (!trekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'trek.label', default: 'Trek'), id])
            redirect(action: "list")
            return
        }

        [trekInstance: trekInstance]
    }

    def edit(Long id) {
        def trekInstance = Trek.get(id)
        if (!trekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'trek.label', default: 'Trek'), id])
            redirect(action: "list")
            return
        }

        [trekInstance: trekInstance]
    }

    def update(Long id, Long version) {
        def trekInstance = Trek.get(id)
        if (!trekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'trek.label', default: 'Trek'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (trekInstance.version > version) {
                trekInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'trek.label', default: 'Trek')] as Object[],
                          "Another user has updated this Trek while you were editing")
                render(view: "edit", model: [trekInstance: trekInstance])
                return
            }
        }

        trekInstance.properties = params

        if (!trekInstance.save(flush: true)) {
            render(view: "edit", model: [trekInstance: trekInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'trek.label', default: 'Trek'), trekInstance.id])
        redirect(action: "show", id: trekInstance.id)
    }

    def delete(Long id) {
        def trekInstance = Trek.get(id)
        if (!trekInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'trek.label', default: 'Trek'), id])
            redirect(action: "list")
            return
        }

        try {
            trekInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'trek.label', default: 'Trek'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'trek.label', default: 'Trek'), id])
            redirect(action: "show", id: id)
        }
    }
}
