import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICompetenzeLng } from 'app/shared/model/competenze-lng.model';
import { CompetenzeLngService } from './competenze-lng.service';

@Component({
  templateUrl: './competenze-lng-delete-dialog.component.html'
})
export class CompetenzeLngDeleteDialogComponent {
  competenzeLng?: ICompetenzeLng;

  constructor(
    protected competenzeLngService: CompetenzeLngService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.competenzeLngService.delete(id).subscribe(() => {
      this.eventManager.broadcast('competenzeLngListModification');
      this.activeModal.close();
    });
  }
}
