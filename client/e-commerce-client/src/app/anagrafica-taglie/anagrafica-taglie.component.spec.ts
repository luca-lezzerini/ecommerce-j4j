import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnagraficaTaglieComponent } from './anagrafica-taglie.component';

describe('AnagraficaTaglieComponent', () => {
  let component: AnagraficaTaglieComponent;
  let fixture: ComponentFixture<AnagraficaTaglieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnagraficaTaglieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnagraficaTaglieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
