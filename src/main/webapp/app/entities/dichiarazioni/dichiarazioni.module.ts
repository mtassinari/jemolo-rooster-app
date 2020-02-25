import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { DichiarazioniComponent } from './dichiarazioni.component';
import { DichiarazioniDetailComponent } from './dichiarazioni-detail.component';
import { DichiarazioniUpdateComponent } from './dichiarazioni-update.component';
import { DichiarazioniDeleteDialogComponent } from './dichiarazioni-delete-dialog.component';
import { dichiarazioniRoute } from './dichiarazioni.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(dichiarazioniRoute)],
  declarations: [DichiarazioniComponent, DichiarazioniDetailComponent, DichiarazioniUpdateComponent, DichiarazioniDeleteDialogComponent],
  entryComponents: [DichiarazioniDeleteDialogComponent]
})
export class JemoloRoosterAppDichiarazioniModule {}
