import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnagraficaProdottiComponent } from './anagrafica-prodotti.component';

describe('AnagraficaProdottiComponent', () => {
  let component: AnagraficaProdottiComponent;
  let fixture: ComponentFixture<AnagraficaProdottiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnagraficaProdottiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnagraficaProdottiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
