import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ElencoTaglieComponent } from './elenco-taglie.component';

describe('ElencoTaglieComponent', () => {
  let component: ElencoTaglieComponent;
  let fixture: ComponentFixture<ElencoTaglieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ElencoTaglieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ElencoTaglieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
