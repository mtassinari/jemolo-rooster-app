import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { CompetenzaComponent } from './competenza.component';
import { CompetenzaDetailComponent } from './competenza-detail.component';
import { CompetenzaUpdateComponent } from './competenza-update.component';
import { CompetenzaDeleteDialogComponent } from './competenza-delete-dialog.component';
import { competenzaRoute } from './competenza.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(competenzaRoute)],
  declarations: [CompetenzaComponent, CompetenzaDetailComponent, CompetenzaUpdateComponent, CompetenzaDeleteDialogComponent],
  entryComponents: [CompetenzaDeleteDialogComponent]
})
export class JemoloRoosterAppCompetenzaModule {}
