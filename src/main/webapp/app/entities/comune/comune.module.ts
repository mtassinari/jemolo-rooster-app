import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { ComuneComponent } from './comune.component';
import { ComuneDetailComponent } from './comune-detail.component';
import { ComuneUpdateComponent } from './comune-update.component';
import { ComuneDeleteDialogComponent } from './comune-delete-dialog.component';
import { comuneRoute } from './comune.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(comuneRoute)],
  declarations: [ComuneComponent, ComuneDetailComponent, ComuneUpdateComponent, ComuneDeleteDialogComponent],
  entryComponents: [ComuneDeleteDialogComponent],
})
export class JemoloRoosterAppComuneModule {}
