import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { CompetenzeLngComponent } from './competenze-lng.component';
import { CompetenzeLngDetailComponent } from './competenze-lng-detail.component';
import { CompetenzeLngUpdateComponent } from './competenze-lng-update.component';
import { CompetenzeLngDeleteDialogComponent } from './competenze-lng-delete-dialog.component';
import { competenzeLngRoute } from './competenze-lng.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(competenzeLngRoute)],
  declarations: [CompetenzeLngComponent, CompetenzeLngDetailComponent, CompetenzeLngUpdateComponent, CompetenzeLngDeleteDialogComponent],
  entryComponents: [CompetenzeLngDeleteDialogComponent]
})
export class JemoloRoosterAppCompetenzeLngModule {}
